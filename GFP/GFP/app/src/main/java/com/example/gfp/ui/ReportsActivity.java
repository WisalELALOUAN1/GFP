package com.example.gfp.ui;

import static com.example.gfp.ui.NavbarManager.setupNavbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gfp.R;
import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.Transaction;
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
import java.util.List;
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

    @Inject
    CategoryRepository categoryRepository;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Spinner reportTypeSpinner;
    private MaterialButton exportPdfButton;
    private String userEmail;
    private String selectedReportType = "transactions";

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
        exportButton.setOnClickListener(v -> exportCombinedReport());
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
                // 1) Vider uniquement les infos de connexion dans SharedPreferences
                SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
                prefs.edit()
                        .remove("key_user_id")   // ou le nom exact de ta clé userId
                        .remove("key_token")     // si tu stockes un token
                        .apply();

                // 2) Retourner vers l’écran de login en vidant la pile d’activités
                Intent intent = new Intent(ReportsActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // 3) Fermer l’activité courante
                finish();
            }
        });
    }

    public int getUserId() {
        return sessionManager.getUserId();
    }
    // Dans ReportsActivity
    private void exportCombinedReport() {
        int userId = sessionManager.getUserId();
        List<Transaction> transactions = transactionRepository.getTransactionsByUser(userId);
        List<Goal> allGoals = goalRepository.getAllGoals(userId);

        // Create PDF exporter instance
        PdfExporter pdfExporter = new PdfExporter(this, "Financial Report");

        // Generate and save the report
        File pdfFile = pdfExporter.generateReport(
                transactions,
                allGoals,
                userCategoryRepository,
                categoryRepository
        );

        if (pdfFile != null) {
            Toast.makeText(this, "Report saved : " , Toast.LENGTH_LONG).show();

            // Optional: Share the PDF
            // sharePdfFile(pdfFile);
        } else {
            Toast.makeText(this, "Failed to generate report", Toast.LENGTH_SHORT).show();
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