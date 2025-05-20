package com.example.gfp.utils;

import android.content.Context;
import android.graphics.*;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;

import com.example.gfp.R;
import com.example.gfp.data.model.*;
import com.example.gfp.data.repository.*;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

public class PdfExporter {
    // Document dimensions
    private static final int PAGE_WIDTH = 595;  // A4 width in points (210mm)
    private static final int PAGE_HEIGHT = 842; // A4 height in points (297mm)
    private static final int MARGIN = 40;
    private static final int HEADER_HEIGHT = 60;
    private static final int FOOTER_HEIGHT = 40;

    // Color palette
    private static final int PRIMARY_COLOR = Color.rgb(106, 34, 2);  // maron (#6a2202)
    private static final int SECONDARY_COLOR = Color.rgb(188, 116, 4); // moutarde (#bc7404)
    private static final int SUCCESS_COLOR = Color.rgb(132, 76, 44); // maron2 (#844c2c)
    private static final int WARNING_COLOR = Color.rgb(214, 168, 97); // moutarde2 (#d6a861)
    private static final int DANGER_COLOR = Color.rgb(173, 78, 31); // A darker shade of maron for warnings
    private static final int LIGHT_GRAY = Color.rgb(245, 245, 245); // light_gray_background
    private static final int DARK_TEXT = Color.rgb(51, 51, 51); // text_dark (#333333)
    private static final int LIGHT_TEXT = Color.rgb(117, 117, 117); // secondary_text (#757575)
    private static final int BACKGROUND_COLOR = Color.rgb(249, 244, 236); // background_gray (#F9F4EC)
    private static final int ACCENT_COLOR = Color.rgb(214, 188, 170);

    // Font sizes
    private static final float TITLE_SIZE = 24f;
    private static final float HEADER_SIZE = 16f;
    private static final float BODY_SIZE = 12f;
    private static final float SMALL_TEXT = 10f;

    private final Context context;
    private final PdfDocument document;
    private PdfDocument.Page currentPage;
    private Canvas canvas;
    private int currentY;
    private int pageNumber = 1;
    private final String reportTitle;
    private final Date reportDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

    public PdfExporter(Context context, String title) {
        this.context = context;
        this.document = new PdfDocument();
        this.reportTitle = title;
        this.reportDate = new Date();
        createNewPage();
    }

    public File generateReport(List<Transaction> transactions,
                               List<Goal> goals,
                               UserCategoryRepository userCatRepo,
                               CategoryRepository catRepo) {

        File pdfFile = null;
        FileOutputStream outputStream = null;

        try {
            // 1. Cover page


            // 2. Summary page

            drawSummary(transactions, goals);

            // 3. Goals section
            createNewPage();
            drawGoalsSection(goals);

            // 4. Transactions section
            createNewPage();
            drawTransactionsSection(transactions, userCatRepo, catRepo);

            // 5. Finish the last page
            if (currentPage != null) {
                drawFooter();
                document.finishPage(currentPage);
                currentPage = null; // Prevent accidental reuse
            }

            // 6. Generate filename and save
            String fileName = "financial_report_" +
                    new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + ".pdf";
            pdfFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);

            outputStream = new FileOutputStream(pdfFile);
            document.writeTo(outputStream); // Now safe to write

            return pdfFile;

        } catch (Exception e) {
            Log.e("PDFExport", "Error generating report", e);
            if (pdfFile != null && pdfFile.exists()) {
                pdfFile.delete(); // Clean up if something went wrong
            }
            return null;
        } finally {
            // 7. Ensure resources are closed
            if (document != null) {
                document.close();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    Log.e("PDFExport", "Error closing stream", e);
                }
            }
        }
    }
    private void drawTransactionsSection(List<Transaction> transactions,
                                         UserCategoryRepository userCatRepo,
                                         CategoryRepository catRepo) {
        // Section title
        drawSectionTitle("Transaction History", PRIMARY_COLOR);

        if (transactions.isEmpty()) {
            drawNoDataMessage("No transactions found for selected period");
            return;
        }

        // Table header
        String[] headers = {"Date", "Description", "Amount", "Category", "Type"};
        float[] columnWidths = {100, 180, 90, 120, 80};
        drawTableHeader(headers, columnWidths);

        // Group transactions by date
        Map<String, List<Transaction>> transactionsByDate = transactions.stream()
                .collect(Collectors.groupingBy(tx -> {
                    try {
                        return formatTransactionDate(tx);
                    } catch (Exception e) {
                        return "Other";
                    }
                }));

        // Table rows with date grouping
        Paint paint = new Paint();
        paint.setTextSize(BODY_SIZE);
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("EEE, MMM d yyyy", Locale.US);

        for (Map.Entry<String, List<Transaction>> entry : transactionsByDate.entrySet()) {
            // Draw date header
            paint.setColor(PRIMARY_COLOR);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(entry.getKey(), MARGIN, currentY, paint);
            currentY += 20;

            // Draw transactions for this date
            paint.setColor(DARK_TEXT);
            paint.setTypeface(Typeface.DEFAULT);

            for (Transaction tx : entry.getValue()) {
                String category = getCategoryName(tx, userCatRepo, catRepo);
                String amount = formatCurrency(tx.getAmount());
                int amountColor = "Income".equals(tx.getType()) ? SUCCESS_COLOR : DANGER_COLOR;

                drawTableRow(
                        new String[]{
                                "", // Empty date cell since we have date header
                                tx.getDescription(),
                                amount,
                                category,
                                tx.getType()
                        },
                        columnWidths,
                        amountColor
                );

                // Check for page overflow
                if (currentY > PAGE_HEIGHT - FOOTER_HEIGHT - 30) {
                    createNewPage();
                    drawTableHeader(new String[]{"...Continued"}, new float[]{PAGE_WIDTH - 2*MARGIN});
                }
            }

            // Add space between date groups
            currentY += 10;
        }
    }
    private String getCategoryName(Transaction tx,
                                   UserCategoryRepository userCatRepo,
                                   CategoryRepository catRepo) {


        UserCategory userCat = userCatRepo.getById(tx.getIdUserCategory());
        if (userCat == null) return "Uncategorized";

        String catName = catRepo.getCategoryName(userCat.getIdCategory());
        return catName != null ? catName : "Uncategorized";
    }

    private void createNewPage() {
        if (currentPage != null) {
            drawFooter();
            document.finishPage(currentPage);
        }

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageNumber).create();
        currentPage = document.startPage(pageInfo);
        canvas = currentPage.getCanvas();
        currentY = MARGIN + HEADER_HEIGHT;

        // Don't draw header for cover page (pageNumber starts at 1)
        if (pageNumber > 1) {
            drawHeader();
        }
        pageNumber++;
    }

    private void drawCoverPage() {
        Paint paint = new Paint();
        Canvas canvas = currentPage.getCanvas();

        // 1. Fond dégradé avec les nouvelles couleurs marron
        Shader gradient = new LinearGradient(0, 0, PAGE_WIDTH, PAGE_HEIGHT,
                Color.rgb(106, 34, 2), // maron
                Color.rgb(214, 188, 170), // maron3
                Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        canvas.drawRect(0, 0, PAGE_WIDTH, PAGE_HEIGHT, paint);
        paint.setShader(null);

        // 2. Motif décoratif en haut et en bas (lignes ondulées)
        paint.setColor(Color.rgb(214, 168, 97)); // moutarde2
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        // Motif en haut
        Path topPath = new Path();
        for (int i = 0; i < PAGE_WIDTH; i += 20) {
            if (i == 0) {
                topPath.moveTo(i, 30 + (float)(Math.sin(i/20.0) * 10));
            } else {
                topPath.lineTo(i, 30 + (float)(Math.sin(i/20.0) * 10));
            }
        }
        canvas.drawPath(topPath, paint);

        // Motif en bas
        Path bottomPath = new Path();
        for (int i = 0; i < PAGE_WIDTH; i += 20) {
            if (i == 0) {
                bottomPath.moveTo(i, PAGE_HEIGHT - 30 + (float)(Math.sin(i/20.0) * 10));
            } else {
                bottomPath.lineTo(i, PAGE_HEIGHT - 30 + (float)(Math.sin(i/20.0) * 10));
            }
        }
        canvas.drawPath(bottomPath, paint);

        // 3. Titre principal avec effet d'ombre
        paint.setColor(Color.WHITE);
        paint.setTextSize(42);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        paint.setShadowLayer(5, 2, 2, Color.rgb(80, 25, 1)); // Ombre légère
        drawCenteredText(reportTitle, PAGE_HEIGHT/2, paint);
        paint.clearShadowLayer();

        // 4. Date du rapport
        paint.setColor(Color.rgb(249, 244, 236)); // background_gray
        paint.setTextSize(18);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        drawCenteredText("Rapport généré le " + dateFormat.format(reportDate),
                PAGE_HEIGHT/2 + 50, paint);

        // 5. Élément décoratif (séparateur)
        paint.setColor(Color.rgb(214, 168, 97)); // moutarde2
        paint.setStrokeWidth(3);
        canvas.drawLine(PAGE_WIDTH/4, PAGE_HEIGHT/2 + 100,
                3*PAGE_WIDTH/4, PAGE_HEIGHT/2 + 100, paint);

        // 6. Sous-titre ou slogan
        paint.setColor(Color.WHITE);
        paint.setTextSize(22);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        drawCenteredText("Une vision claire de vos finances", PAGE_HEIGHT/2 + 150, paint);

        // 7. Copyright ou mention légale en bas de page
        paint.setColor(Color.WHITE);
        paint.setTextSize(10);
        paint.setTypeface(Typeface.DEFAULT);
        drawCenteredText("© GFP Financial App " + Calendar.getInstance().get(Calendar.YEAR),
                PAGE_HEIGHT - 50, paint);
    }
    private void drawHeader() {
        Paint paint = new Paint();
        paint.setColor(LIGHT_GRAY);
        canvas.drawRect(0, 0, PAGE_WIDTH, HEADER_HEIGHT, paint);

        paint.setColor(PRIMARY_COLOR);
        paint.setTextSize(14);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(reportTitle, MARGIN, HEADER_HEIGHT - 20, paint);

        String pageText = "Page " + (pageNumber - 1);
        float textWidth = paint.measureText(pageText);
        canvas.drawText(pageText, PAGE_WIDTH - MARGIN - textWidth, HEADER_HEIGHT - 20, paint);

        paint.setStrokeWidth(1);
        canvas.drawLine(MARGIN, HEADER_HEIGHT - 2, PAGE_WIDTH - MARGIN, HEADER_HEIGHT - 2, paint);
    }

    private void drawFooter() {
        Paint paint = new Paint();
        paint.setColor(LIGHT_TEXT);
        paint.setTextSize(SMALL_TEXT);

        String footerText = "Generated by GFP Financial App";
        canvas.drawText(footerText, MARGIN, PAGE_HEIGHT - 20, paint);

        String dateText = dateFormat.format(reportDate);
        float dateWidth = paint.measureText(dateText);
        canvas.drawText(dateText, PAGE_WIDTH - MARGIN - dateWidth, PAGE_HEIGHT - 20, paint);
    }

    private void drawSummary(List<Transaction> transactions, List<Goal> goals) {
        Paint paint = new Paint();

        // Section title
        drawSectionTitle("Financial Summary", PRIMARY_COLOR);

        // Key metrics
        double income = transactions.stream()
                .filter(t -> "credit".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expenses = transactions.stream()
                .filter(t -> "debit".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = income - expenses;

        int activeGoals = (int) goals.stream()
                .filter(g -> !g.isCompleted() && !g.isExpired())
                .count();

        // Draw metrics
        drawMetricCard("Total Income", formatCurrency(income), SUCCESS_COLOR, MARGIN, currentY);
        drawMetricCard("Total Expenses", formatCurrency(expenses), DANGER_COLOR, PAGE_WIDTH/2, currentY);
        currentY += 80;

        drawMetricCard("Net Balance", formatCurrency(balance),
                balance >= 0 ? SUCCESS_COLOR : DANGER_COLOR,
                MARGIN, currentY);
        drawMetricCard("Active Goals", String.valueOf(activeGoals), PRIMARY_COLOR, PAGE_WIDTH/2, currentY);
        currentY += 100;

        // Expense breakdown
        drawExpenseBreakdown(transactions);
    }

    private void drawMetricCard(String title, String value, int color, float x, float y) {
        Paint paint = new Paint();

        // Card background
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + 250, y + 70, paint);

        // Border
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(x, y, x + 250, y + 70, paint);

        // Title
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DARK_TEXT);
        paint.setTextSize(BODY_SIZE);
        canvas.drawText(title, x + 10, y + 20, paint);

        // Value
        paint.setColor(color);
        paint.setTextSize(18);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(value, x + 10, y + 50, paint);
    }

    private void drawExpenseBreakdown(List<Transaction> transactions) {
        // Implement category breakdown chart here
        // Similar to previous example but with enhanced styling
    }

    private void drawGoalsSection(List<Goal> goals) {
        // Section title
        drawSectionTitle("Financial Goals Overview", PRIMARY_COLOR);

        if (goals.isEmpty()) {
            drawNoDataMessage("No goals found for selected period");
            return;
        }

        // Summary cards
        drawGoalSummaryCards(goals);
        currentY += 30;

        // Detailed table
        String[] headers = {"Goal", "Target", "Saved", "Progress", "Due Date", "Status"};
        float[] columnWidths = {150, 80, 80, 100, 100, 80};
        drawTableHeader(headers, columnWidths);

        // Sort by due date (ascending)
        goals.sort(Comparator.comparing(Goal::getDateFin));

        Paint paint = new Paint();
        paint.setTextSize(BODY_SIZE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

        for (Goal goal : goals) {
            double progress = (goal.getSommeEpargne() / goal.getBudgetTotal()) * 100;
            String status = goal.isCompleted() ? "Completed" :
                    goal.isExpired() ? "Expired" :
                            new Date().after(new Date(goal.getDateFin())) ? "Overdue" : "Active";

            int statusColor = getGoalStatusColor(status);
            String dueDate = dateFormat.format(new Date(goal.getDateFin()));

            drawTableRow(
                    new String[]{
                            goal.getDescription(),
                            formatCurrency(goal.getBudgetTotal()),
                            formatCurrency(goal.getSommeEpargne()),
                            String.format(Locale.US, "%.1f%%", progress),
                            dueDate,
                            status
                    },
                    columnWidths,
                    statusColor
            );

            // Check for page overflow
            if (currentY > PAGE_HEIGHT - FOOTER_HEIGHT - 30) {
                createNewPage();
                drawTableHeader(new String[]{"...Continued"}, new float[]{PAGE_WIDTH - 2*MARGIN});
            }
        }
    }
    private void drawSummaryCard(String title, String value, int color, float x, float y, float width) {
        Paint paint = new Paint();

        // Card background with shadow effect
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(4f, 2f, 2f, Color.argb(50, 0, 0, 0));
        canvas.drawRoundRect(x, y, x + width, y + 80, 8f, 8f, paint);
        paint.clearShadowLayer();

        // Card border
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
        canvas.drawRoundRect(x, y, x + width, y + 80, 8f, 8f, paint);

        // Title text
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DARK_TEXT);
        paint.setTextSize(12f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText(title, x + 10, y + 20, paint);

        // Value text
        paint.setColor(color);
        paint.setTextSize(18f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        // Measure text to center it horizontally
        float textWidth = paint.measureText(value);
        float textX = x + (width - textWidth) / 2;
        canvas.drawText(value, textX, y + 55, paint);

        // Optional: Add icon based on card type
        if (title.contains("Completed")) {
            drawCheckmarkIcon(x + width - 30, y + 20, SUCCESS_COLOR);
        } else if (title.contains("Expired")) {
            drawWarningIcon(x + width - 30, y + 20, DANGER_COLOR);
        }
    }

    // Helper methods for icons (optional)
    private void drawCheckmarkIcon(float x, float y, int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);
        paint.setStrokeCap(Paint.Cap.ROUND);

        Path path = new Path();
        path.moveTo(x, y + 10);
        path.lineTo(x + 8, y + 18);
        path.lineTo(x + 20, y + 6);
        canvas.drawPath(path, paint);
    }

    private void drawWarningIcon(float x, float y, int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);

        // Triangle
        Path path = new Path();
        path.moveTo(x + 10, y);
        path.lineTo(x + 20, y + 20);
        path.lineTo(x, y + 20);
        path.close();
        canvas.drawPath(path, paint);

        // Exclamation mark
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x + 10, y + 25, 2f, paint);
        canvas.drawRect(x + 8, y + 28, x + 12, y + 32, paint);
    }
    private void drawGoalSummaryCards(List<Goal> goals) {
        // Calculate metrics
        int totalGoals = goals.size();
        int completed = (int) goals.stream().filter(Goal::isCompleted).count();
        int active = (int) goals.stream().filter(g -> !g.isCompleted() && !g.isExpired()).count();
        int expired = (int) goals.stream().filter(g -> !g.isCompleted() && g.isExpired()).count();

        double totalTarget = goals.stream().mapToDouble(Goal::getBudgetTotal).sum();
        double totalSaved = goals.stream().mapToDouble(Goal::getSommeEpargne).sum();
        double overallProgress = totalTarget > 0 ? (totalSaved / totalTarget * 100) : 0;

        // Draw cards in 2 rows
        int cardWidth = (PAGE_WIDTH - 3 * MARGIN) / 2;

        // Row 1
        drawSummaryCard("Total Goals", String.valueOf(totalGoals),
                PRIMARY_COLOR, MARGIN, currentY, cardWidth);
        drawSummaryCard("Completed", completed + " (" + (totalGoals > 0 ?
                        (100*completed/totalGoals) + "%)" : "0%") ,
                SUCCESS_COLOR, MARGIN + cardWidth + 10, currentY, cardWidth);
        currentY += 90;

        // Row 2
        drawSummaryCard("Active Goals", String.valueOf(active),
                WARNING_COLOR, MARGIN, currentY, cardWidth);
        drawSummaryCard("Expired", String.valueOf(expired),
                DANGER_COLOR, MARGIN + cardWidth + 10, currentY, cardWidth);
        currentY += 90;

        // Progress bar
        drawProgressBar("Overall Progress", overallProgress, MARGIN, currentY, PAGE_WIDTH - 2*MARGIN);
        currentY += 40;
    }

    private int getGoalStatusColor(String status) {
        switch (status) {
            case "Completed": return SUCCESS_COLOR;
            case "Expired": return DARK_TEXT;
            case "Overdue": return DANGER_COLOR;
            case "Active": return PRIMARY_COLOR;
            default: return LIGHT_TEXT;
        }
    }

    private void drawProgressBar(String label, double percent, float x, float y, float width) {
        Paint paint = new Paint();

        // Background
        paint.setColor(LIGHT_GRAY);
        canvas.drawRect(x, y, x + width, y + 20, paint);

        // Progress
        paint.setColor(PRIMARY_COLOR);
        canvas.drawRect(x, y, x + (float)(width * percent / 100), y + 20, paint);

        // Border
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(DARK_TEXT);
        canvas.drawRect(x, y, x + width, y + 20, paint);

        // Text
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(BODY_SIZE);
        paint.setColor(Color.WHITE);
        String text = label + ": " + String.format(Locale.US, "%.1f%%", percent);
        canvas.drawText(text, x + 10, y + 15, paint);
    }


    private String formatTransactionDate(Transaction tx) {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("MMM dd", Locale.US);
        SimpleDateFormat dateStringFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd", Locale.US);

        try {
            // First try to parse as timestamp
            return timestampFormat.format(new Date(Long.parseLong(tx.getTime())));
        } catch (NumberFormatException e1) {
            try {
                // If not a timestamp, try parsing as date string
                return outputFormat.format(dateStringFormat.parse(tx.getTime()));
            } catch (Exception e2) {
                Log.e("PDFExport", "Couldn't parse date: " + tx.getTime());
                return tx.getTime(); // Return raw string as fallback
            }
        }
    }
    private void drawSectionTitle(String title, int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setTextSize(HEADER_SIZE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(title, MARGIN, currentY, paint);
        currentY += 30;
    }

    private void drawTableHeader(String[] headers, float[] columnWidths) {
        Paint paint = new Paint();
        paint.setColor(PRIMARY_COLOR);
        paint.setTextSize(BODY_SIZE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        float x = MARGIN;
        for (int i = 0; i < headers.length; i++) {
            canvas.drawText(headers[i], x, currentY, paint);
            x += columnWidths[i];
        }

        // Underline
        paint.setStrokeWidth(1);
        canvas.drawLine(MARGIN, currentY + 5, PAGE_WIDTH - MARGIN, currentY + 5, paint);

        currentY += 25;
    }

    private void drawTableRow(String[] values, float[] columnWidths, int highlightColor) {
        Paint paint = new Paint();
        paint.setTextSize(BODY_SIZE);

        float x = MARGIN;
        for (int i = 0; i < values.length; i++) {
            // Highlight amount/status columns
            if (i == values.length - 1 || i == values.length - 2) {
                paint.setColor(highlightColor);
            } else {
                paint.setColor(DARK_TEXT);
            }

            canvas.drawText(values[i], x, currentY, paint);
            x += columnWidths[i];
        }

        currentY += 20;

        // Add space every 5 rows
        if ((pageNumber + 4) % 5 == 0) {
            currentY += 10;
        }

        // Check for page overflow
        if (currentY > PAGE_HEIGHT - FOOTER_HEIGHT - 30) {
            createNewPage();
            drawTableHeader(new String[]{"...Continued"}, new float[]{PAGE_WIDTH - 2*MARGIN});
        }
    }

    private void drawNoDataMessage(String message) {
        Paint paint = new Paint();
        paint.setColor(LIGHT_TEXT);
        paint.setTextSize(BODY_SIZE);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        canvas.drawText(message, MARGIN, currentY, paint);
        currentY += 30;
    }

    private void drawCenteredText(String text, float y, Paint paint) {
        float x = (PAGE_WIDTH - paint.measureText(text)) / 2;
        canvas.drawText(text, x, y, paint);
    }

    private String formatCurrency(double amount) {
        return String.format(Locale.US, "$%.2f", amount);
    }

}