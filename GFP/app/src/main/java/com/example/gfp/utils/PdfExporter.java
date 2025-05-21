package com.example.gfp.utils;

import android.content.Context;
import android.graphics.*;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;

import com.example.gfp.data.model.*;
import com.example.gfp.data.repository.*;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

import io.realm.Realm;

public class PdfExporter {
    // Document dimensions (A4)
    private static final int PAGE_WIDTH = 595;
    private static final int PAGE_HEIGHT = 842;
    private static final int MARGIN = 50;
    private static final int HEADER_HEIGHT = 60;
    private static final int FOOTER_HEIGHT = 40;

    // Moderne palette de couleurs
    private static final int PRIMARY_COLOR = Color.rgb(43, 108, 176);  // Bleu (#2B6CB0)
    private static final int SUCCESS_COLOR = Color.rgb(56, 161, 105);  // Vert (#38A169)
    private static final int WARNING_COLOR = Color.rgb(237, 137, 54);  // Orange (#ED8936)
    private static final int DANGER_COLOR = Color.rgb(229, 62, 62);    // Rouge (#E53E3E)
    private static final int LIGHT_GRAY = Color.rgb(247, 250, 252);    // Gris clair (#F7FAFC)
    private static final int DARK_TEXT = Color.rgb(45, 55, 72);        // Texte foncé (#2D3748)
    private static final int LIGHT_TEXT = Color.rgb(113, 128, 150);    // Texte léger (#718096)

    // Tailles de police
    private static final float TITLE_SIZE = 24f;
    private static final float HEADER_SIZE = 16f;
    private static final float BODY_SIZE = 11f;
    private static final float SMALL_TEXT = 9f;

    private final Context context;
    private final PdfDocument document;
    private PdfDocument.Page currentPage;
    private Canvas canvas;
    private int currentY;
    private int pageNumber = 1;
    private final String reportTitle;
    private final Date reportDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());

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
                               CategoryRepository catRepo) throws IOException {
        File pdfFile = null;
        FileOutputStream outputStream = null;

        try {
            Log.d("PDFExport", "Starting PDF generation");

            // Commence avec première page
            pageNumber = 1;
            currentY = MARGIN + HEADER_HEIGHT;

            // 1) Page de couverture
            drawCoverPage();
            createNewPage();

            // 2) Statistiques
            drawSummary(transactions, goals);

            // 3) Section objectifs
            createNewPage();
            drawGoalsSection(goals);

            // 4) Section transactions
            createNewPage();
            drawTransactionsSection(transactions, userCatRepo, catRepo);

            // 5) Finir dernière page
            if (currentPage != null) {
                drawFooter();
                document.finishPage(currentPage);
                currentPage = null;
            }

            // 6) Génère nom de fichier horodaté
            String fileName = "financial_report_" +
                    new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
                            .format(new Date()) + ".pdf";

            // 7) Récupère dossier Téléchargements
            File downloadsDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!downloadsDir.exists() && !downloadsDir.mkdirs()) {
                Log.e("PDFExport", "Impossible de créer le dossier Téléchargements");
            }

            // 8) Crée fichier final
            pdfFile = new File(downloadsDir, fileName);
            Log.d("PDFExport", "Saving PDF to: " + pdfFile.getAbsolutePath());

            // 9) Écrit document
            outputStream = new FileOutputStream(pdfFile);
            document.writeTo(outputStream);
            outputStream.flush();

            Log.d("PDFExport", "PDF generation completed successfully");
            return pdfFile;

        } catch (Exception e) {
            Log.e("PDFExport", "Error generating report", e);
            if (pdfFile != null && pdfFile.exists() && !pdfFile.delete()) {
                Log.w("PDFExport", "Failed to delete incomplete PDF file");
            }
            return null;

        } finally {
            if (outputStream != null) {
                try { outputStream.close(); }
                catch (IOException e) {
                    Log.e("PDFExport", "Error closing stream", e);
                }
            }
            if (document != null) {
                try {
                    if (currentPage != null) {
                        document.finishPage(currentPage);
                        currentPage = null;
                    }
                    document.close();
                } catch (Exception e) {
                    Log.e("PDFExport", "Error closing document", e);
                }
            }
        }
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

        if (pageNumber > 1) {
            drawHeader();
        }
        pageNumber++;
    }

    private void drawCoverPage() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Dégradé bleu moderne
        Shader gradient = new LinearGradient(0, 0, 0, PAGE_HEIGHT,
                PRIMARY_COLOR,
                Color.rgb(54, 172, 170), // Turquoise
                Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, PAGE_WIDTH, PAGE_HEIGHT, paint);
        paint.setShader(null);

        // Grand cercle décoratif
        paint.setColor(Color.argb(30, 255, 255, 255));
        canvas.drawCircle(PAGE_WIDTH - 150, 200, 100, paint);

        // Petit cercle décoratif
        paint.setColor(Color.argb(20, 255, 255, 255));
        canvas.drawCircle(150, PAGE_HEIGHT - 200, 70, paint);

        // Titre du rapport
        paint.setColor(Color.WHITE);
        paint.setTextSize(42);
        paint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        paint.setShadowLayer(5, 1, 1, Color.argb(50, 0, 0, 0));
        drawCenteredText(reportTitle, PAGE_HEIGHT/2 - 50, paint);
        paint.clearShadowLayer();

        // Sous-titre
        paint.setColor(Color.rgb(240, 240, 240));
        paint.setTextSize(18);
        paint.setTypeface(Typeface.create("sans-serif-light", Typeface.ITALIC));
        drawCenteredText("Rapport généré le " + dateFormat.format(reportDate),
                PAGE_HEIGHT/2 + 20, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        canvas.drawLine(PAGE_WIDTH/4, PAGE_HEIGHT/2 + 60,
                3*PAGE_WIDTH/4, PAGE_HEIGHT/2 + 60, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(22);
        paint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        drawCenteredText("Votre parcours financier en un coup d'œil", PAGE_HEIGHT/2 + 120, paint);

        paint.setColor(Color.rgb(220, 220, 220));
        paint.setTextSize(10);
        paint.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        drawCenteredText("© DirhamWay Financial App " + Calendar.getInstance().get(Calendar.YEAR),
                PAGE_HEIGHT - 50, paint);
    }

    private void drawHeader() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setColor(LIGHT_GRAY);
        canvas.drawRect(0, 0, PAGE_WIDTH, HEADER_HEIGHT, paint);

        paint.setColor(PRIMARY_COLOR);
        paint.setStrokeWidth(3);
        canvas.drawLine(0, HEADER_HEIGHT, PAGE_WIDTH, HEADER_HEIGHT, paint);

        paint.setColor(PRIMARY_COLOR);
        paint.setTextSize(14);
        paint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        canvas.drawText(reportTitle, MARGIN, HEADER_HEIGHT - 20, paint);

        String pageText = "Page " + (pageNumber - 1);
        float textWidth = paint.measureText(pageText);

        paint.setColor(PRIMARY_COLOR);
        RectF rect = new RectF(PAGE_WIDTH - MARGIN - textWidth - 20, HEADER_HEIGHT - 35,
                PAGE_WIDTH - MARGIN + 10, HEADER_HEIGHT - 5);
        canvas.drawRoundRect(rect, 15, 15, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText(pageText, PAGE_WIDTH - MARGIN - textWidth - 5, HEADER_HEIGHT - 15, paint);
    }

    private void drawFooter() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setColor(Color.rgb(230, 230, 230));
        paint.setStrokeWidth(1);
        canvas.drawLine(MARGIN, PAGE_HEIGHT - FOOTER_HEIGHT,
                PAGE_WIDTH - MARGIN, PAGE_HEIGHT - FOOTER_HEIGHT, paint);

        paint.setColor(LIGHT_TEXT);
        paint.setTextSize(SMALL_TEXT);
        paint.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));

        canvas.drawText("Généré par DirhamWay Financial App", MARGIN, PAGE_HEIGHT - 20, paint);

        String dateText = dateFormat.format(reportDate);
        float dateWidth = paint.measureText(dateText);
        canvas.drawText(dateText, PAGE_WIDTH - MARGIN - dateWidth, PAGE_HEIGHT - 20, paint);
    }

    private void drawSummary(List<Transaction> transactions, List<Goal> goals) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        drawSectionTitle("Aperçu Financier", PRIMARY_COLOR);

        double income = transactions.stream()
                .filter(t -> "credit".equals(t.getType().toLowerCase()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expenses = transactions.stream()
                .filter(t -> "debit".equals(t.getType().toLowerCase()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = income - expenses;

        int activeGoals = (int) goals.stream()
                .filter(g -> !g.isCompleted() && !g.isExpired())
                .count();

        drawMetricCard("Revenus", formatCurrency(income), SUCCESS_COLOR, MARGIN, currentY);
        drawMetricCard("Dépenses", formatCurrency(expenses), DANGER_COLOR, PAGE_WIDTH/2, currentY);
        currentY += 90;

        drawMetricCard("Solde Net", formatCurrency(balance),
                balance >= 0 ? SUCCESS_COLOR : DANGER_COLOR,
                MARGIN, currentY);
        drawMetricCard("Objectifs Actifs", String.valueOf(activeGoals), PRIMARY_COLOR, PAGE_WIDTH/2, currentY);
        currentY += 100;
    }

    private void drawMetricCard(String title, String value, int color, float x, float y) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(6f, 0f, 3f, Color.argb(25, 0, 0, 0));
        RectF rect = new RectF(x, y, x + 240, y + 80);
        canvas.drawRoundRect(rect, 10f, 10f, paint);
        paint.clearShadowLayer();

        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        RectF topBorder = new RectF(x, y, x + 240, y + 5);
        canvas.drawRect(topBorder, paint);

        paint.setColor(DARK_TEXT);
        paint.setTextSize(12);
        paint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        canvas.drawText(title, x + 15, y + 25, paint);

        paint.setColor(color);
        paint.setTextSize(22);
        paint.setTypeface(Typeface.create("sans-serif-light", Typeface.BOLD));
        canvas.drawText(value, x + 15, y + 55, paint);
    }

    private void drawGoalsSection(List<Goal> goals) {
        drawSectionTitle("Objectifs Financiers", PRIMARY_COLOR);

        if (goals.isEmpty()) {
            drawNoDataMessage("Aucun objectif trouvé pour la période sélectionnée");
            return;
        }

        int totalGoals = goals.size();
        int completed = (int) goals.stream().filter(Goal::isCompleted).count();
        int active = (int) goals.stream().filter(g -> !g.isCompleted() && !g.isExpired()).count();
        int expired = (int) goals.stream().filter(g -> !g.isCompleted() && g.isExpired()).count();

        double totalTarget = goals.stream().mapToDouble(Goal::getBudgetTotal).sum();
        double totalSaved = goals.stream().mapToDouble(Goal::getSommeEpargne).sum();
        double overallProgress = totalTarget > 0 ? (totalSaved / totalTarget * 100) : 0;

        // Tableaux
        String[] headers = {"Objectif", "Cible", "Épargné", "Progrès", "Échéance", "Statut"};
        float[] columnWidths = {150, 80, 80, 100, 100, 80};
        drawTableHeader(headers, columnWidths);

        // Trier par date d'échéance
        goals.sort(Comparator.comparing(Goal::getDateFin));

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(BODY_SIZE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        for (Goal goal : goals) {
            double progress = (goal.getSommeEpargne() / goal.getBudgetTotal()) * 100;
            String status;

            if (goal.isCompleted()) {
                status = "Terminé";
            } else if (goal.isExpired()) {
                status = "Expiré";
            } else if (new Date().after(new Date(goal.getDateFin()))) {
                status = "En retard";
            } else {
                status = "Actif";
            }

            int statusColor = getGoalStatusColor(status);
            String dueDate = dateFormat.format(new Date(goal.getDateFin()));

            drawTableRow(
                    new String[]{
                            goal.getDescription(),
                            formatCurrency(goal.getBudgetTotal()),
                            formatCurrency(goal.getSommeEpargne()),
                            String.format(Locale.getDefault(), "%.1f%%", progress),
                            dueDate,
                            status
                    },
                    columnWidths,
                    statusColor
            );

            // Vérifier débordement de page
            if (currentY > PAGE_HEIGHT - FOOTER_HEIGHT - 30) {
                createNewPage();
                drawTableHeader(headers, columnWidths);
            }
        }
    }

    private void drawTransactionsSection(List<Transaction> transactions,
                                         UserCategoryRepository userCatRepo,
                                         CategoryRepository catRepo) {
        drawSectionTitle("Historique des Transactions", PRIMARY_COLOR);

        if (transactions.isEmpty()) {
            drawNoDataMessage("Aucune transaction trouvée pour la période sélectionnée");
            return;
        }

        // En-tête tableau
        String[] headers = {"Date", "Description", "Montant", "Catégorie", "Type"};
        float[] columnWidths = {100, 180, 90, 120, 80};
        drawTableHeader(headers, columnWidths);

        // Grouper par date
        Map<String, List<Transaction>> transactionsByDate = transactions.stream()
                .collect(Collectors.groupingBy(tx -> {
                    try {
                        return formatTransactionDate(tx);
                    } catch (Exception e) {
                        return "Autre";
                    }
                }));

        // Trier dates (plus récentes d'abord)
        List<String> sortedDates = new ArrayList<>(transactionsByDate.keySet());
        Collections.sort(sortedDates, Collections.reverseOrder());

        // Lignes du tableau
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(BODY_SIZE);

        for (String dateKey : sortedDates) {
            List<Transaction> txForDate = transactionsByDate.get(dateKey);

            // En-tête de date
            paint.setColor(PRIMARY_COLOR);
            paint.setTypeface(Typeface.DEFAULT_BOLD);

            // Fond d'en-tête de date
            paint.setColor(Color.argb(10, 43, 108, 176)); // Très léger bleu
            canvas.drawRect(MARGIN - 10, currentY - 15, PAGE_WIDTH - MARGIN, currentY + 5, paint);

            paint.setColor(PRIMARY_COLOR);
            canvas.drawText(dateKey, MARGIN, currentY, paint);
            currentY += 25;

            // Transactions de cette date
            paint.setColor(DARK_TEXT);
            paint.setTypeface(Typeface.DEFAULT);

            for (Transaction tx : txForDate) {
                String category = getCategoryName(tx, userCatRepo, catRepo);
                String amount = formatCurrency(tx.getAmount());
                int amountColor = "credit".equals(tx.getType().toLowerCase()) ? SUCCESS_COLOR : DANGER_COLOR;

                drawTableRow(
                        new String[]{
                                "", // Cellule date vide car on a un en-tête
                                tx.getDescription(),
                                amount,
                                category,
                                tx.getType()
                        },
                        columnWidths,
                        amountColor
                );

                // Vérifier débordement de page
                if (currentY > PAGE_HEIGHT - FOOTER_HEIGHT - 30) {
                    createNewPage();
                    drawTableHeader(headers, columnWidths);
                }
            }

            // Espace entre groupes de dates
            currentY += 15;
        }
    }

    private String getCategoryName(Transaction tx,
                                   UserCategoryRepository userCatRepo,
                                   CategoryRepository catRepo) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();

            UserCategory userCat = realm.where(UserCategory.class)
                    .equalTo("id", tx.getIdUserCategory())
                    .findFirst();

            if (userCat == null) return "Non catégorisé";

            Category category = realm.where(Category.class)
                    .equalTo("id", userCat.getIdCategory())
                    .findFirst();

            return category != null ? category.getCategoryName() : "Non catégorisé";

        } catch (Exception e) {
            Log.e("PDFExport", "Error getting category name", e);
            return "Non catégorisé";
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
    }

    private void drawSectionTitle(String title, int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Fond de titre
        paint.setColor(Color.argb(10, 43, 108, 176)); // Bleu très clair
        RectF rect = new RectF(MARGIN - 15, currentY - 22, PAGE_WIDTH - MARGIN + 15, currentY + 10);
        canvas.drawRoundRect(rect, 5f, 5f, paint);

        // Bordure gauche
        paint.setColor(color);
        RectF accentRect = new RectF(MARGIN - 15, currentY - 22, MARGIN - 10, currentY + 10);
        canvas.drawRect(accentRect, paint);

        // Texte titre
        paint.setColor(color);
        paint.setTextSize(HEADER_SIZE);
        paint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        canvas.drawText(title, MARGIN, currentY, paint);

        currentY += 45;
    }

    private void drawTableHeader(String[] headers, float[] columnWidths) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Fond d'en-tête
        paint.setColor(Color.rgb(240, 245, 250)); // Gris-bleu clair
        canvas.drawRect(MARGIN - 10, currentY - 20, PAGE_WIDTH - MARGIN, currentY + 10, paint);

        // Texte en-tête
        paint.setColor(PRIMARY_COLOR);
        paint.setTextSize(BODY_SIZE);
        paint.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));

        float x = MARGIN;
        for (int i = 0; i < headers.length; i++) {
            canvas.drawText(headers[i], x, currentY, paint);
            x += columnWidths[i];
        }

        // Bordure inférieure
        paint.setColor(PRIMARY_COLOR);
        paint.setStrokeWidth(2);
        canvas.drawLine(MARGIN - 10, currentY + 5, PAGE_WIDTH - MARGIN, currentY + 5, paint);

        currentY += 25;
    }

    private void drawTableRow(String[] values, float[] columnWidths, int highlightColor) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(BODY_SIZE);

        // Lignes alternées
        if (currentY % 50 < 25) {
            paint.setColor(Color.argb(5, 0, 0, 0)); // Gris très subtil
            canvas.drawRect(MARGIN - 10, currentY - 15, PAGE_WIDTH - MARGIN, currentY + 5, paint);
        }

        float x = MARGIN;
        for (int i = 0; i < values.length; i++) {
            if (i == 2) { // Colonne montant
                paint.setColor(highlightColor);
                paint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            } else {
                paint.setColor(DARK_TEXT);
                paint.setTypeface(Typeface.DEFAULT);
            }

            canvas.drawText(values[i], x, currentY, paint);
            x += columnWidths[i];
        }

        currentY += 20;
    }

    private void drawNoDataMessage(String message) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Boîte info avec fond subtil
        paint.setColor(Color.argb(10, 100, 100, 100));
        RectF rect = new RectF(MARGIN, currentY - 20, PAGE_WIDTH - MARGIN, currentY + 20);
        canvas.drawRoundRect(rect, 8f, 8f, paint);

        // Icône info
        paint.setColor(LIGHT_TEXT);
        canvas.drawCircle(MARGIN + 15, currentY, 10, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(14);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("i", MARGIN + 12, currentY + 5, paint);

        // Message texte
        paint.setColor(LIGHT_TEXT);
        paint.setTextSize(BODY_SIZE);
        paint.setTypeface(Typeface.create("sans-serif", Typeface.ITALIC));
        canvas.drawText(message, MARGIN + 40, currentY + 5, paint);

        currentY += 40;
    }

    private String formatTransactionDate(Transaction tx) {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
        SimpleDateFormat dateStringFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM", Locale.getDefault());

        try {
            // Essayer timestamp d'abord
            return timestampFormat.format(new Date(Long.parseLong(tx.getTime())));
        } catch (NumberFormatException e1) {
            try {
                // Sinon essayer string date
                return outputFormat.format(dateStringFormat.parse(tx.getTime()));
            } catch (Exception e2) {
                Log.e("PDFExport", "Couldn't parse date: " + tx.getTime());
                return tx.getTime(); // Fallback à la chaîne brute
            }
        }
    }

    private int getGoalStatusColor(String status) {
        switch (status) {
            case "Terminé": return SUCCESS_COLOR;
            case "Expiré": return LIGHT_TEXT;
            case "En retard": return DANGER_COLOR;
            case "Actif": return PRIMARY_COLOR;
            default: return LIGHT_TEXT;
        }
    }

    private void drawCenteredText(String text, float y, Paint paint) {
        float x = (PAGE_WIDTH - paint.measureText(text)) / 2;
        canvas.drawText(text, x, y, paint);
    }

    private String formatCurrency(double amount) {
        return String.format(Locale.getDefault(), "%.2f MAD", amount);
    }
}