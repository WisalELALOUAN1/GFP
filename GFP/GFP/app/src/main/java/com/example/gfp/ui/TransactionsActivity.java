package com.example.gfp.ui;

import static com.example.gfp.ui.NavbarManager.setupNavbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.model.Category;
import com.example.gfp.data.model.Transaction;
import com.example.gfp.data.model.UserCategory;
import com.example.gfp.data.repository.TransactionRepository;
import com.example.gfp.data.session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class TransactionsActivity extends AppCompatActivity {

    @Inject
    SessionManager sessionManager;
    @Inject
    TransactionRepository transactionRepository;

    private String userEmail;
    private RecyclerView transactionsRecyclerView;
    private Button addTransactionButton;
    private Spinner categorySpinner;
    private Spinner typeSpinner;
    private TextInputEditText amountInput;
    private TextInputEditText descriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        userEmail = getIntent().getStringExtra("user_email");
        setupNavbar();
        BottomNav.setup(this);

        // Initialisation des vues
        transactionsRecyclerView = findViewById(R.id.transactionsRecyclerView);
        addTransactionButton = findViewById(R.id.addTransactionButton);
        categorySpinner = findViewById(R.id.categorySpinner);
        typeSpinner = findViewById(R.id.typeSpinner);
        amountInput = findViewById(R.id.amountInput);
        descriptionInput = findViewById(R.id.descriptionInput);

        // Configurer les Spinners
        setupSpinners();

        // Charger les transactions existantes
        loadTransactions();

        // Gestion du clic sur le bouton d'ajout
        addTransactionButton.setOnClickListener(v -> addTransaction());
    }

    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                Toast.makeText(TransactionsActivity.this,
                        "Refreshing...",
                        Toast.LENGTH_SHORT).show();

                amountInput.setText("");
                descriptionInput.setText("");
                typeSpinner.setSelection(0);
                categorySpinner.setSelection(0);

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
                Intent intent = new Intent(TransactionsActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // 3) Fermer l’activité courante
                finish();
            }
        });
    }

    private void setupSpinners() {
        // Spinner pour le type (credit/debit)
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.transaction_types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        // Spinner pour les catégories (uniquement celles de l'utilisateur)
        List<String> userCategories = getUserCategories(sessionManager.getUserId());

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, userCategories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

    private List<String> getUserCategories(int userId) {
        Realm realm = Realm.getDefaultInstance();
        try {
            // Récupérer toutes les UserCategory de l'utilisateur
            List<UserCategory> userCategories = realm.where(UserCategory.class)
                    .equalTo("idUser", userId)
                    .findAll();

            // Récupérer les noms des catégories correspondantes
            List<String> categoryNames = new ArrayList<>();
            for (UserCategory uc : userCategories) {
                Category category = realm.where(Category.class)
                        .equalTo("idCategory", uc.getIdCategory())
                        .findFirst();
                if (category != null) {
                    categoryNames.add(category.getCategoryName());
                }
            }
            return categoryNames;
        } finally {
            realm.close();
        }
    }

    private void loadTransactions() {
        int userId = sessionManager.getUserId();
        List<Transaction> transactions = transactionRepository.getTransactionsByUser(userId);
        TransactionsAdapter adapter = new TransactionsAdapter(transactions);
        transactionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        transactionsRecyclerView.setAdapter(adapter);
    }

    private void addTransaction() {
        String type = typeSpinner.getSelectedItem().toString();
        String categoryName = type.equals("debit") ? categorySpinner.getSelectedItem().toString() : "Income";
        String amountText = amountInput.getText().toString();
        String description = descriptionInput.getText().toString();

        if (amountText.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer un montant", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            // Créer un nouvel objet Transaction
            Transaction transaction = new Transaction();
            transaction.setType(type);
            transaction.setAmount(amount);
            transaction.setDescription(description);
            transaction.setTime(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));

            // Pour idUserCategory, utilisez la méthode existante
            if (type.equals("debit")) {
                int idUserCategory = findUserCategoryId(categoryName);
                transaction.setIdUserCategory(idUserCategory);
            }

            // Utilisez le repository pour sauvegarder avec l'ID utilisateur
            transactionRepository.saveTransactionForUser(sessionManager.getUserId(), transaction);

            // Sauvegarder dans le fichier CSV si c'est un débit
            if (type.equals("debit")) {
                saveToCsv(transaction, categoryName);
            }

            loadTransactions();
            clearForm();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Montant invalide", Toast.LENGTH_SHORT).show();
        }
    }

    private int findUserCategoryId(String categoryName) {
        Realm realm = Realm.getDefaultInstance();
        try {
            // Trouver la catégorie par nom
            Category category = realm.where(Category.class)
                    .equalTo("categoryName", categoryName)
                    .findFirst();

            if (category != null) {
                // Trouver l'UserCategory pour cet utilisateur et cette catégorie
                UserCategory userCategory = realm.where(UserCategory.class)
                        .equalTo("idUser", sessionManager.getUserId())
                        .equalTo("idCategory", category.getIdCategory())
                        .findFirst();

                if (userCategory != null) {
                    return userCategory.getIdUserCategory();
                }
            }
            return -1; // ou créer une nouvelle UserCategory si nécessaire
        } finally {
            realm.close();
        }
    }
    private void saveToCsv(Transaction transaction, String categoryName) {
        try {
            FileOutputStream fos = openFileOutput("nouvelles_transactions_200.csv", MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            // Formatage de la date courante
            String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(new Date());

            String line = String.format(Locale.US, "%s,%s,%.2f,%s,%s,%s\n",
                    currentDate,  // Utilisation de la date courante formatée
                    transaction.getDescription(),
                    transaction.getAmount(),
                    transaction.getType(),
                    categoryName,  // Utilisation du paramètre categoryName passé à la méthode
                    "Default Account");

            osw.write(line);
            osw.close();
            fos.close();
        } catch (IOException e) {
            Log.e("CSV_ERROR", "Erreur lors de l'écriture dans le fichier CSV", e);
            Toast.makeText(this, "Erreur lors de la sauvegarde CSV", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        amountInput.setText("");
        descriptionInput.setText("");
    }
}