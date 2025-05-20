package com.example.gfp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.gfp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import io.realm.Realm;

public class DashboardActivity extends AppCompatActivity {

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Récupérer l'email de l'utilisateur
        userEmail = getIntent().getStringExtra("user_email");

        // Configurer la BottomNav
        BottomNav.setup(this);

        // Configurer la navbar avec le NavbarManager
        setupNavbar();
    }

    /**
     * Configure la navbar avec le NavbarManager
     */
    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                // Rafraîchir le dashboard
                refreshDashboard();
                Toast.makeText(DashboardActivity.this, "Tableau de bord actualisé", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // 3) Fermer l’activité courante
                finish();
            }
        });
    }

    /**
     * Méthode pour rafraîchir le dashboard
     */
    private void refreshDashboard() {
        // Implémentez votre logique de rafraîchissement ici
        // Par exemple, recharger les données depuis une base de données ou une API
    }

    // Cette méthode doit exister pour que android:onClick la trouve
    public void onAnalyseCardClick(View view) {
        Intent intent = new Intent(this, AnalyseActivity.class);
        intent.putExtra("user_email", userEmail);
        startActivity(intent);
    }

    // Méthode appelée quand on clique sur la carte Transactions
    public void onTransactionsCardClick(View view) {
        navigateToTransactions();
    }

    // Navigation vers TransactionsActivity
    private void navigateToTransactions() {
        Intent intent = new Intent(this, TransactionsActivity.class);
        intent.putExtra("user_email", userEmail);
        startActivity(intent);
    }

    // Method called when Reports card is clicked
    public void onReportsCardClick(View view) {
        navigateToReports();
    }

    // Navigation to ReportsActivity
    private void navigateToReports() {
        Intent intent = new Intent(this, ReportsActivity.class);
        intent.putExtra("user_email", userEmail);
        startActivity(intent);
    }

    // Méthode appelée quand on clique sur la carte Goals
    public void onGoalsCardClick(View view) {
        navigateToGoalsList();
    }

    // Navigation vers GoalsList
    private void navigateToGoalsList() {
        Intent intent = new Intent(this, GoalsListActivity.class);
        intent.putExtra("user_email", userEmail);
        startActivity(intent);
    }

    // Méthode pour récupérer l'email (utile pour les fragments)
    public String getUserEmail() {
        return userEmail;
    }
}