package com.example.gfp.ui;

import static com.example.gfp.ui.NavbarManager.setupNavbar;
import android.content.ContentValues;
import android.provider.MediaStore;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gfp.R;
import com.example.gfp.data.model.Category;
import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.Transaction;
import com.example.gfp.data.model.UserCategory;
import com.example.gfp.data.repository.CategoryRepository;
import com.example.gfp.data.repository.GoalRepository;
import com.example.gfp.data.repository.TransactionRepository;
import com.example.gfp.data.repository.UserCategoryRepository;
import com.example.gfp.data.session.SessionManager;
import com.example.gfp.utils.PdfExporter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;


@AndroidEntryPoint
public class ReportsActivity extends AppCompatActivity {

    @Inject SessionManager sessionManager;
    @Inject TransactionRepository transactionRepository;

    @Inject GoalRepository goalRepository;
    @Inject
    UserCategoryRepository userCategoryRepository;
    private final ExecutorService pdfExecutor = Executors.newSingleThreadExecutor();
    @Inject
    CategoryRepository categoryRepository;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Spinner reportTypeSpinner;
    private MaterialButton exportPdfButton;
    private String userEmail;
    private String selectedReportType = "transactions";

    private static final int RC_PERM = 1234;

    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        viewPager = findViewById(R.id.viewPager);

        tabLayout = findViewById(R.id.tabLayout);
        ReportsPagerAdapter pagerAdapter = new ReportsPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        setupNavbar();
        BottomNav.setup(this);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(position == 0 ? "Transactions" : "Objectifs");
        }).attach();

        MaterialButton exportButton = findViewById(R.id.exportPdfButton);
        exportButton.setOnClickListener(v -> checkStoragePermissionAndExport());
    }

    private void checkStoragePermissionAndExport() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    RC_PERM);
        } else {
            exportCombinedReport();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERM &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            exportCombinedReport();
        } else {
            Toast.makeText(this,
                    "Permission de stockage refusée",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                recreate();
                Toast.makeText(getApplicationContext(),
                                "Refreshing...",
                                Toast.LENGTH_SHORT)
                        .show();
            }
            @Override
            public void onLogoutClicked() {
                sessionManager.clearSession();

                Intent intent = new Intent(ReportsActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();
            }
        });
    }

    public int getUserId() {
        return sessionManager.getUserId();
    }
    private void exportCombinedReport() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Génération du rapport en cours...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        pdfExecutor.execute(() -> {
            Realm realm = null;
            File pdfFile = null;

            try {
                realm = Realm.getDefaultInstance();

                List<Transaction> transactions = realm.copyFromRealm(
                        realm.where(Transaction.class).findAll()
                );
                List<Goal> goals = realm.copyFromRealm(
                        realm.where(Goal.class).findAll()
                );
                List<UserCategory> userCategories = realm.copyFromRealm(
                        realm.where(UserCategory.class).findAll()
                );
                List<Category> categories = realm.copyFromRealm(
                        realm.where(Category.class).findAll()
                );
                Map<Integer, UserCategory> ucMap = new HashMap<>();
                for (UserCategory uc : userCategories) {
                    ucMap.put(uc.getIdUserCategory(), uc);
                }
                Map<Integer, String> catNameMap = new HashMap<>();
                for (Category c : categories) {
                    catNameMap.put(c.getIdCategory(), c.getCategoryName());
                }

                PdfExporter exporter = new PdfExporter(getApplicationContext(), "Financial Report");
                pdfFile = exporter.generateReport(
                        transactions,
                        goals,
                        new UserCategoryRepository(sessionManager) {
                            @Override public UserCategory getById(int id) {
                                return ucMap.get(id);
                            }
                        },
                        new CategoryRepository() {
                            @Override public String getCategoryName(int id) {
                                return catNameMap.getOrDefault(id, "Uncategorized");
                            }
                        }
                );

                if (pdfFile != null) {
                    Log.d("ReportsActivity", "generateReport renvoyé : " + pdfFile.getAbsolutePath());
                    Log.d("ReportsActivity", "fichier existe ? " + pdfFile.exists() +
                            ", taille = " + pdfFile.length());
                } else {
                    Log.e("ReportsActivity", "generateReport a retourné null");
                }

                final File finalPdf = pdfFile;

                runOnUiThread(() -> {
                    progressDialog.dismiss();

                    if (finalPdf != null && finalPdf.exists()) {
                        Toast.makeText(this,
                                "PDF généré : " + finalPdf.getAbsolutePath(),
                                Toast.LENGTH_LONG).show();

                        openPdfFile(finalPdf);

                    } else {
                        showErrorDialog("La génération du rapport a échoué.\n" +
                                "Vérifiez Logcat pour 'ReportsActivity' et 'PDFExport'.");
                    }
                });

            } catch (Exception e) {
                Log.e("ReportsActivity", "Exception in exportCombinedReport", e);
                runOnUiThread(() -> {
                    progressDialog.dismiss();
                    showErrorDialog("Erreur lors de la génération : " + e.getMessage());
                });

            } finally {
                if (realm != null && !realm.isClosed()) realm.close();
            }
        });
    }



    private void openPdfFile(File pdfFile) {
        try {
            if (pdfFile == null || !pdfFile.exists()) {
                showErrorDialog("Le fichier PDF n'existe pas");
                return;
            }

            Log.d("ReportsActivity", "Opening PDF file: " + pdfFile.getAbsolutePath());

            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri fileUri;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fileUri = androidx.core.content.FileProvider.getUriForFile(
                        this,
                        getApplicationContext().getPackageName() + ".provider",
                        pdfFile
                );
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                fileUri = Uri.fromFile(pdfFile);
            }

            intent.setDataAndType(fileUri, "application/pdf");
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                showErrorDialog("Aucune application pour ouvrir les PDF n'a été trouvée");
            }
        } catch (Exception e) {
            Log.e("ReportsActivity", "Error opening PDF", e);
            showErrorDialog("Erreur lors de l'ouverture du PDF: " + e.getMessage());
        }
    }
    private void showErrorDialog(String message) {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Erreur")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showExportResult(boolean success, String filePath) {
        if (success) {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Rapport généré")
                    .setMessage("Rapport sauvegardé : " + filePath)
                    .setPositiveButton("OK", null)
                    .show();
        } else {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Impossible de générer le rapport")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public GoalRepository getGoalRepository() {
        return goalRepository;
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.report_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reportTypeSpinner.setAdapter(adapter);

        reportTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedReportType = parent.getItemAtPosition(position).toString().toLowerCase();
                loadReportData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    private void loadCurrentGoals(int userId) {
        List<Goal> goals = goalRepository.getCurrentGoals(userId);
        Log.d("ReportsActivity", "Current goals count: " + goals.size());

        if (goals.isEmpty()) {
            Toast.makeText(this, "Aucun objectif en cours", Toast.LENGTH_SHORT).show();
        }

        GoalsAdapter adapter = new GoalsAdapter(this, goals);

    }
    private void loadReportData() {
        int userId = sessionManager.getUserId();

        switch (selectedReportType) {
            case "transactions":
                loadTransactions(userId);
                break;
            case "objectifs en cours":
                loadCurrentGoals(userId);
                break;
            case "objectifs terminés":
                loadCompletedGoals(userId);
                break;
            case "objectifs expirés":
                loadExpiredGoals(userId);
                break;
        }
    }

    private void loadTransactions(int userId) {
        List<Transaction> transactions = transactionRepository.getTransactionsByUser(userId);
        TransactionsAdapter adapter = new TransactionsAdapter(transactions);
    }



    private void loadCompletedGoals(int userId) {
        List<Goal> goals = goalRepository.getCompletedGoals(userId);
        GoalsAdapter adapter = new GoalsAdapter(this, goals);
    }

    private void loadExpiredGoals(int userId) {
        List<Goal> goals = goalRepository.getExpiredGoals(userId);
        GoalsAdapter adapter = new GoalsAdapter(this, goals);
    }





}