package com.example.gfp.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider; // Assurez-vous d'importer ViewModelProvider

import androidx.appcompat.app.AppCompatActivity;

import com.example.gfp.R;
import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.User;
import com.example.gfp.viewmodel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class ModifyGoalActivity extends AppCompatActivity {

    private EditText etGoalDescription, etBudgetTotal, etSommeEpargne;
    private UserViewModel userVM;
    private Goal goalToModify;
    private TextView tvSelectedDate;
    private Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_goal);

        // Initialisation des vues
        etGoalDescription = findViewById(R.id.et_goal_description);
        etBudgetTotal = findViewById(R.id.et_budget_total);
        etSommeEpargne = findViewById(R.id.et_somme_epargne);
        tvSelectedDate = findViewById(R.id.tv_selected_date);

        userVM = new ViewModelProvider(this).get(UserViewModel.class);

        // Récupérer l'ID de l'objectif et l'email de l'utilisateur
        int goalId = getIntent().getIntExtra("goal_id", -1);
        LinearLayout datePickerContainer = findViewById(R.id.date_picker_container);
        datePickerContainer.setOnClickListener(v -> showDatePickerDialog());

        // Ajoutez ceci pour le bouton de sauvegarde
        CardView btnSaveGoal = findViewById(R.id.btn_save_goal);
        btnSaveGoal.setOnClickListener(v -> {
            String userEmail = getIntent().getStringExtra("user_email");
            saveModifiedGoal(userEmail);
        });

        // Charger l'objectif à modifier à partir de l'ID
        goalToModify = userVM.getGoalById(goalId);

        // Charger l'objectif directement depuis Realm
        try (Realm realm = Realm.getDefaultInstance()) {
            goalToModify = realm.where(Goal.class)
                    .equalTo("idObj", goalId)
                    .findFirst();

            if (goalToModify != null) {
                // Copier l'objet pour l'utiliser hors transaction
                goalToModify = realm.copyFromRealm(goalToModify);

                // Pré-remplir les champs
                etGoalDescription.setText(goalToModify.getDescription());
                etBudgetTotal.setText(String.valueOf(goalToModify.getBudgetTotal()));
                etSommeEpargne.setText(String.valueOf(goalToModify.getSommeEpargne()));
                tvSelectedDate.setText(goalToModify.getDateFin());

            } else {
                Toast.makeText(this, "Objectif introuvable.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // Méthode pour afficher le DatePickerDialog
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Mettre à jour la date
                        calendar.set(year, monthOfYear, dayOfMonth);
                        selectedDate = calendar.getTime();

                        // Mettre à jour l'affichage de la date
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        tvSelectedDate.setText(sdf.format(selectedDate));
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    private void saveModifiedGoal(String userEmail) {
        if (goalToModify == null) {
            Toast.makeText(this, "Goal not found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get input values
        String description = etGoalDescription.getText().toString();
        String budgetStr = etBudgetTotal.getText().toString();
        String initialSavingsStr = etSommeEpargne.getText().toString();

        // Validate required fields
        if (description.isEmpty() || budgetStr.isEmpty() || initialSavingsStr.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try (Realm realm = Realm.getDefaultInstance()) {
            // Parse values before transaction
            double budgetTotal = Double.parseDouble(budgetStr);
            double initialSavings = Double.parseDouble(initialSavingsStr);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = selectedDate != null ? sdf.format(selectedDate) : null;

            realm.executeTransaction(r -> {
                // Get fresh managed object directly from Realm
                Goal managedGoal = r.where(Goal.class)
                        .equalTo("idObj", goalToModify.getIdObj())
                        .findFirst();

                if (managedGoal != null) {
                    boolean isModified = false;

                    if (!description.equals(managedGoal.getDescription())) {
                        managedGoal.setDescription(description);
                        isModified = true;
                    }

                    if (budgetTotal != managedGoal.getBudgetTotal()) {
                        managedGoal.setBudgetTotal(budgetTotal);
                        isModified = true;
                    }

                    if (initialSavings != managedGoal.getSommeEpargne()) {
                        managedGoal.setSommeEpargne(initialSavings);
                        isModified = true;
                    }

                    if (formattedDate != null && !formattedDate.equals(managedGoal.getDateFin())) {
                        managedGoal.setDateFin(formattedDate);
                        isModified = true;
                    }

                    if (!isModified) {
                        r.cancelTransaction();
                    }
                }
            });

            // Show success message
            Toast.makeText(this, "Goal updated successfully!", Toast.LENGTH_SHORT).show();

            // Navigate back

            finish();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error updating goal: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}









