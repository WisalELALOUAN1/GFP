// com/example/gfp/ui/MonthlyBudgetActivity.java
package com.example.gfp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gfp.R;
import com.example.gfp.data.model.User;
import com.example.gfp.viewmodel.MonthlyBudgetViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;
import com.example.gfp.data.session.SessionManager;
@AndroidEntryPoint
public class MonthlyBudgetActivity extends AppCompatActivity {

    private TextInputEditText budgetInput;
    private MaterialButton continueButton;
    private MonthlyBudgetViewModel viewModel;
    private Realm realm;
    @Inject
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_budget);

        budgetInput    = findViewById(R.id.budget_input);
        continueButton = findViewById(R.id.continue_button);

        // Récupère le ViewModel injecté par Hilt
        viewModel = new ViewModelProvider(this)
                .get(MonthlyBudgetViewModel.class);
        // Initialize Realm
        realm = Realm.getDefaultInstance();
        // Observe le résultat de la sauvegarde
        viewModel.getSaveSuccess().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                updateFirstLoginStatus();
                Toast.makeText(this,
                        "Monthly budget saved!", Toast.LENGTH_SHORT).show();
                // Navigation vers ChargesActivity
                Intent intent = new Intent(
                        MonthlyBudgetActivity.this,
                        ChargesActivity.class
                );
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this,
                        "Error saving budget", Toast.LENGTH_SHORT).show();
            }
        });

        continueButton.setOnClickListener(v -> {
            String txt = budgetInput.getText().toString().trim();
            if (txt.isEmpty()) {
                Toast.makeText(this,
                        "Please enter your monthly budget",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                double bd = Double.parseDouble(txt);
                // Délègue la logique au ViewModel
                viewModel.saveMonthlyBudget(bd);
            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Invalid number format",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFirstLoginStatus() {
        int userId = session.getUserId();
        if (userId < 0) return;

        realm.executeTransactionAsync(realm -> {
            User user = realm.where(User.class)
                    .equalTo("idUser", userId)
                    .findFirst();
            if (user != null) {
                user.setFirstLogin(0); // Set firstLogin to false
            }
        });
    }
}
