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
import com.example.gfp.data.session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class DashboardActivity extends AppCompatActivity {
    @Inject
    SessionManager sessionManager;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userEmail = sessionManager.getUserEmail();
        BottomNav.setup(this);
        setupNavbar();
    }

    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                refreshDashboard();
                Toast.makeText(DashboardActivity.this, "Tableau de bord actualisé", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLogoutClicked() {
                sessionManager.clearSession();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
    }

    private void refreshDashboard() {
    }

    public void onAnalyseCardClick(View view) {
        Intent intent = new Intent(this, AnalyseActivity.class);
        intent.putExtra("user_email", userEmail);
        startActivity(intent);
    }

    public void onTransactionsCardClick(View view) {
        navigateToTransactions();
    }

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