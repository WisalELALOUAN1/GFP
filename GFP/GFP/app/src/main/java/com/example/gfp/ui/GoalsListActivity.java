package com.example.gfp.ui;

import static com.example.gfp.ui.NavbarManager.setupNavbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.model.User;
import com.example.gfp.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class GoalsListActivity extends AppCompatActivity {

    private RecyclerView goalsRecyclerView;
    private GoalsAdapter adapter;
    private UserViewModel userVM;
    private String userEmail;

    @Override
    protected void onResume() {
        super.onResume();
        loadUserGoals(); // Recharger les objectifs chaque fois que l'on revient dans cette activité
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_list);

        // Récupérer l'email de l'utilisateur
        userEmail = getIntent().getStringExtra("user_email");
        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        setupNavbar();
        BottomNav.setup(this);
        // Configuration du RecyclerView
        setupRecyclerView();
        loadUserGoals();

        // Récupérer le bouton "Ajouter un objectif" et lui assigner un onClickListener
        FloatingActionButton addGoalButton = findViewById(R.id.addGoalButton);
        addGoalButton.setOnClickListener(view -> {
            // Créer un Intent pour ouvrir DefineGoalActivity
            Intent intent = new Intent(GoalsListActivity.this, DefineGoalActivity.class);
            // Passer l'email de l'utilisateur à l'activité suivante
            intent.putExtra("user_email", userEmail);
            startActivity(intent);


        });
    }

    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                Toast.makeText(GoalsListActivity.this,
                        "Refresh...",
                        Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(GoalsListActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // 3) Fermer l’activité courante
                finish();
            }
        });
    }
    // Getter pour userEmail
    public String getUserEmail() {
        return userEmail;
    }


    private void setupRecyclerView() {
        goalsRecyclerView = findViewById(R.id.goalsRecyclerView);
        goalsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Passer le contexte de l'activité à l'adaptateur
        adapter = new GoalsAdapter(this); // Fournir le contexte ici
        goalsRecyclerView.setAdapter(adapter);
    }


    private void loadUserGoals() {
        Log.d("GoalsListActivity", " loadUserGoals() called — userEmail=" + userEmail);

        User user = userVM.getUserByEmail(userEmail);

        if (user != null) {
            Log.d("GoalsListActivity", " User trouvé, nombre d’objectifs=" +
                    (user.getGoals() != null ? user.getGoals().size() : "null"));
        } else {
            Log.d("GoalsListActivity", " Aucun user retourné pour cet email");
        }

        if (user != null && user.getGoals() != null) {
            adapter.setGoals(user.getGoals());
            adapter.notifyDataSetChanged();
        }
    }
}