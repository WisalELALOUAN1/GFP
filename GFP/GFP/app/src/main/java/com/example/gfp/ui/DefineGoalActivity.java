package com.example.gfp.ui;

import static com.example.gfp.ui.NavbarManager.setupNavbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gfp.R;
import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.User;
import com.example.gfp.viewmodel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class DefineGoalActivity extends AppCompatActivity {

    private  UserViewModel userVM ; // Ou utilisez viewModels() pour obtenir une instance du ViewModel
    private EditText etGoalDescription, etBudgetTotal, etSommeEpargne;
    private TextView tvSelectedDate;
    private ImageView btnBack;
    private String userEmail;
    private Date selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_goal);
        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        // Récupérer l'email de l'utilisateur depuis l'Intent
        userEmail = getIntent().getStringExtra("user_email");
        setupNavbar();

        // Initialiser les vues
        etGoalDescription = findViewById(R.id.et_goal_description);
        etBudgetTotal = findViewById(R.id.et_budget_total);
        etSommeEpargne = findViewById(R.id.et_somme_epargne);
        tvSelectedDate = findViewById(R.id.tv_selected_date);
        btnBack = findViewById(R.id.btn_back);

        // Configurer le bouton de retour
        btnBack.setOnClickListener(view -> onBackPressed());

        // Sélection de la date cible via le LinearLayout
        // Sélection de la date cible via le LinearLayout
        findViewById(R.id.date_picker_container).setOnClickListener(view -> {
            // Utiliser un DatePickerDialog pour sélectionner la date
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    DefineGoalActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    (view1, year, month, dayOfMonth) -> {
                        // Mise à jour de la date sélectionnée
                        selectedDate = new Date(year - 1900, month, dayOfMonth); // L'année commence à partir de 1900
                        updateSelectedDate();
                    },
                    // Utilisez la date actuelle si aucune date n'est sélectionnée
                    selectedDate != null ? selectedDate.getYear() + 1900 : 2023,
                    selectedDate != null ? selectedDate.getMonth() : 0,
                    selectedDate != null ? selectedDate.getDate() : 1
            );
            datePickerDialog.show();
        });


        // Configurer le bouton de sauvegarde
        findViewById(R.id.btn_save_goal).setOnClickListener(view -> saveGoal());
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
                SharedPreferences prefs = getSharedPreferences("app_session", MODE_PRIVATE);
                prefs.edit()
                        .remove("key_user_id")
                        .remove("key_token")
                        .apply();

                Intent intent = new Intent(DefineGoalActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();
            }
        });
    }
    private void saveGoal() {
        String description = etGoalDescription.getText().toString();
        String budgetStr = etBudgetTotal.getText().toString();
        String initialSavingsStr = etSommeEpargne.getText().toString();

        if (description.isEmpty() || budgetStr.isEmpty() || initialSavingsStr.isEmpty() || selectedDate == null) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return;
        }

        double budgetTotal = Double.parseDouble(budgetStr);
        double initialSavings = Double.parseDouble(initialSavingsStr);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String targetDate = dateFormat.format(selectedDate);

        // Créer l'objectif financier
        Goal newGoal = new Goal();
        newGoal.setDescription(description);
        newGoal.setBudgetTotal(budgetTotal);
        newGoal.setSommeEpargne(initialSavings);
        newGoal.setDateFin(targetDate);

        // Calculer la progression
        double progress = (initialSavings / budgetTotal) * 100;

        // Mettre à jour le ProgressBar et le texte
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        TextView progressText = findViewById(R.id.tv_progress_text);
        progressBar.setProgress((int) progress);
        progressText.setText((int) progress + "%");

        // Récupérer l'utilisateur et ajouter l'objectif via ViewModel
        User user = userVM.getUserByEmail(userEmail);
        if (user != null) {
            // Récupérer le dernier ID et l'incrémenter
            int newGoalId = getNextGoalId(user);
            newGoal.setIdObj(newGoalId);

            userVM.addGoalToUser(user, newGoal); // Ajouter l'objectif via ViewModel
            userVM.saveUserProfile(user); // Sauvegarder l'utilisateur avec le nouvel objectif

            Toast.makeText(this, "Objectif ajouté avec succès !", Toast.LENGTH_SHORT).show();
            finish(); // Fermer l'activité après avoir ajouté l'objectif
        } else {
            Toast.makeText(this, "Erreur, utilisateur non trouvé.", Toast.LENGTH_SHORT).show();
        }
    }
    private int getNextGoalId(User user) {
        int maxId = 0;
        if (user.getGoals() != null && !user.getGoals().isEmpty()) {
            for (Goal goal : user.getGoals()) {
                if (goal.getIdObj() > maxId) {
                    maxId = goal.getIdObj();
                }
            }
        }
        return maxId + 1;
    }


    private void updateSelectedDate() {
        if (selectedDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            tvSelectedDate.setText(dateFormat.format(selectedDate));
        }
    }
}
