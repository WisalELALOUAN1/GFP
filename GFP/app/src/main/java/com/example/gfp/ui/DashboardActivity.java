package com.example.gfp.ui;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gfp.R;
import com.example.gfp.data.model.Category;
import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.User;
import com.example.gfp.data.model.UserCategory;
import com.example.gfp.data.session.SessionManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;
import io.realm.RealmResults;

@AndroidEntryPoint
public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";

    @Inject
    SessionManager sessionManager;

    private String userEmail;
    private Realm realm;

    private TextView tvUserName;
    private LineChart goalsChart;
    private PieChart categoryChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        realm = Realm.getDefaultInstance();
        userEmail = sessionManager.getUserEmail();

        Log.d(TAG, "Utilisateur connecté: " + userEmail);

        initializeUI();

        BottomNav.setup(this);

        setupNavbar();

        setupGoalsChart();
        setupCategoryChart();
    }

    private void initializeUI() {
        tvUserName = findViewById(R.id.tvUserName);
        goalsChart = findViewById(R.id.goalsChart);
        categoryChart = findViewById(R.id.categoryChart);

        String displayName = userEmail.split("@")[0];
        tvUserName.setText(displayName);
        Log.d(TAG, "Nom d'affichage: " + displayName);
    }

    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                refreshDashboard();
                Toast.makeText(DashboardActivity.this, "Dashboard updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLogoutClicked() {
                sessionManager.clearSession();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
    }

    private void refreshDashboard() {
        setupGoalsChart();
        setupCategoryChart();
    }

    private void setupGoalsChart() {
        try {
            List<Entry> entries = getGoalsProgressData();

            if (entries.isEmpty()) {
                Log.w(TAG, "Aucune donnée d'objectifs trouvée pour l'utilisateur");
                goalsChart.clear();
                goalsChart.invalidate();
                return;
            }

            LineDataSet dataSet = new LineDataSet(entries, "Goal Progress");
            dataSet.setColor(Color.parseColor("#844c2c"));
            dataSet.setLineWidth(2f);
            dataSet.setCircleColor(Color.parseColor("#844c2c"));
            dataSet.setCircleRadius(4f);
            dataSet.setDrawValues(false);
            dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            LineData lineData = new LineData(dataSet);
            goalsChart.setData(lineData);

            goalsChart.getDescription().setEnabled(false);
            goalsChart.getLegend().setEnabled(true);
            goalsChart.getLegend().setTextColor(Color.parseColor("#844c2c"));
            goalsChart.setDrawGridBackground(false);

            List<String> labels = getGoalLabels();

            XAxis xAxis = goalsChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
            xAxis.setTextColor(Color.parseColor("#844c2c"));
            xAxis.setDrawGridLines(false);

            goalsChart.getAxisRight().setEnabled(false);

            YAxis leftAxis = goalsChart.getAxisLeft();
            leftAxis.setTextColor(Color.parseColor("#844c2c"));
            leftAxis.setGridColor(Color.parseColor("#E6C287"));
            leftAxis.setGridLineWidth(0.5f);
            leftAxis.setAxisLineColor(Color.parseColor("#844c2c"));
            leftAxis.setAxisMinimum(0f);
            leftAxis.setAxisMaximum(100f);

            goalsChart.animateX(1000);
            goalsChart.invalidate();

            Log.d(TAG, "Graphique des objectifs configuré avec succès");
        } catch (Exception e) {
            Log.e(TAG, "Erreur lors de la configuration du graphique des objectifs", e);
            goalsChart.clear();
            goalsChart.invalidate();
        }
    }

    private List<String> getGoalLabels() {
        List<String> labels = new ArrayList<>();

        try {
            int userId = getUserIdFromEmail();
            if (userId <= 0) {
                return labels;
            }

            User user = realm.where(User.class)
                    .equalTo("idUser", userId)
                    .findFirst();

            if (user != null && user.getGoals() != null && !user.getGoals().isEmpty()) {
                for (Goal goal : user.getGoals()) {
                    String label = getShortDescription(goal.getDescription());
                    labels.add(label);
                    Log.d(TAG, "Libellé d'objectif ajouté: " + label);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Erreur lors de la récupération des libellés d'objectifs", e);
        }

        return labels;
    }

    private String getShortDescription(String description) {
        if (description != null && description.length() > 10) {
            return description.substring(0, 7) + "...";
        }
        return description;
    }

    private List<Entry> getGoalsProgressData() {
        List<Entry> entries = new ArrayList<>();

        try {
            int userId = getUserIdFromEmail();
            if (userId <= 0) {
                return entries;
            }

            User user = realm.where(User.class)
                    .equalTo("idUser", userId)
                    .findFirst();

            if (user != null && user.getGoals() != null && !user.getGoals().isEmpty()) {
                int i = 0;
                for (Goal goal : user.getGoals()) {
                    float progress = (float) goal.getProgression();
                    entries.add(new Entry(i++, progress));
                    Log.d(TAG, "Progression d'objectif ajouté: " + progress + "%");
                }
            } else {
                Log.w(TAG, "Aucune donnée de progression d'objectifs pour l'utilisateur");
            }
        } catch (Exception e) {
            Log.e(TAG, "Erreur lors de la récupération des données de progression des objectifs", e);
        }

        return entries;
    }

    private void setupCategoryChart() {
        try {
            List<PieEntry> entries = getUserCategoryData();

            if (entries.isEmpty()) {
                Log.w(TAG, "Aucune donnée de catégorie pour l'utilisateur");
                categoryChart.clear();
                categoryChart.setNoDataText("No category data available");
                categoryChart.setNoDataTextColor(Color.parseColor("#844c2c"));
                categoryChart.invalidate();
                return;
            }

            PieDataSet dataSet = new PieDataSet(entries, "");
            dataSet.setValueTextSize(12f);
            dataSet.setValueTextColor(Color.WHITE);
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueFormatter(new PercentFormatter(categoryChart));

            PieData data = new PieData(dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(12f);
            data.setValueTextColor(Color.WHITE);

            categoryChart.setData(data);
            categoryChart.setUsePercentValues(true);
            categoryChart.getDescription().setEnabled(false);
            categoryChart.setHoleRadius(40f);
            categoryChart.setTransparentCircleRadius(45f);
            categoryChart.setDrawEntryLabels(true);
            categoryChart.setEntryLabelColor(Color.BLACK);
            categoryChart.setEntryLabelTextSize(12f);
            categoryChart.setDrawCenterText(true);
            categoryChart.setCenterText("Budget");
            categoryChart.setCenterTextSize(16f);
            categoryChart.setCenterTextColor(Color.parseColor("#844c2c"));
            categoryChart.getLegend().setEnabled(true);
            categoryChart.getLegend().setTextColor(Color.parseColor("#844c2c"));
            categoryChart.animateY(1000);
            categoryChart.invalidate();

            Log.d(TAG, "Graphique des catégories configuré avec succès");
        } catch (Exception e) {
            Log.e(TAG, "Erreur lors de la configuration du graphique des catégories", e);
            categoryChart.clear();
            categoryChart.setNoDataText("Error loading category data");
            categoryChart.setNoDataTextColor(Color.RED);
            categoryChart.invalidate();
        }
    }

    private List<PieEntry> getUserCategoryData() {
        List<PieEntry> entries = new ArrayList<>();

        try {
            int userId = getUserIdFromEmail();
            if (userId <= 0) {
                Log.e(TAG, "Invalid userId: " + userId);
                return entries;
            }

            Log.d(TAG, "Récupération des catégories pour userId=" + userId);

            realm.refresh();

            RealmResults<UserCategory> userCats = realm.where(UserCategory.class)
                    .equalTo("idUser", userId)
                    .findAll();

            if (userCats.isEmpty()) {
                Log.w(TAG, "Aucune catégorie trouvée pour userId=" + userId);
                return entries;
            }

            Log.d(TAG, "Nombre de catégories trouvées: " + userCats.size());

            double totalBudget = 0;
            for (UserCategory uc : userCats) {
                if (uc.getFinalBudget() > 0) {
                    totalBudget += uc.getFinalBudget();
                }
            }

            if (totalBudget <= 0) {
                Log.w(TAG, "Budget total est zéro ou négatif: " + totalBudget);
                return entries;
            }

            Log.d(TAG, "Budget total: " + totalBudget);

            for (UserCategory uc : userCats) {
                if (uc.getFinalBudget() <= 0) {
                    continue;
                }

                Category cat = realm.where(Category.class)
                        .equalTo("idCategory", uc.getIdCategory())
                        .findFirst();

                String categoryName;
                if (cat != null && cat.getCategoryName() != null && !cat.getCategoryName().isEmpty()) {
                    categoryName = cat.getCategoryName();
                } else {
                    categoryName = "Category " + uc.getIdCategory();
                }

                float percentage = (float) ((uc.getFinalBudget() / totalBudget) * 100);

                if (percentage > 0) {
                    Log.d(TAG, String.format("Ajout catégorie: %s (%.2f%%)", categoryName, percentage));
                    entries.add(new PieEntry(percentage, categoryName));
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "Erreur lors de la récupération des données de catégories", e);
        }

        return entries;
    }

    private int getUserIdFromEmail() {
        if (userEmail == null || userEmail.isEmpty()) {
            Log.e(TAG, "Email utilisateur est null ou vide");
            return -1;
        }

        try {
            realm.refresh();

            User user = realm.where(User.class)
                    .equalTo("email", userEmail)
                    .findFirst();

            if (user != null) {
                int id = user.getIdUser();
                Log.d(TAG, "ID utilisateur trouvé: " + id + " pour email: " + userEmail);
                return id;
            } else {
                Log.e(TAG, "Aucun utilisateur trouvé pour email: " + userEmail);
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, "Erreur lors de la récupération de l'ID utilisateur pour email: " + userEmail, e);
            return -1;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }

    public String getUserEmail() {
        return userEmail;
    }
}