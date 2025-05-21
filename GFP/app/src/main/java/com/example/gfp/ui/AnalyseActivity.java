package com.example.gfp.ui;

import static com.example.gfp.ui.NavbarManager.setupNavbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.gfp.MyApplication;
import com.example.gfp.R;
import com.example.gfp.data.model.Category;
import com.example.gfp.data.model.User;
import com.example.gfp.data.model.UserCategory;
import com.example.gfp.data.repository.UserCategoryRepository;
import com.example.gfp.data.session.SessionManager;
import com.google.android.material.textfield.TextInputEditText;
import java.util.*;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;
import io.realm.RealmResults;

@AndroidEntryPoint
public class AnalyseActivity extends AppCompatActivity {

    private TextInputEditText budgetEditText;
    private Button predictButton;
    private TextView totalBudgetText;
    private TextView recommendationText;
    private LinearLayout visualBudgetContainer;
    private LinearLayout detailedResultsContainer;
    private ProgressBar loadingIndicator;
    @Inject
    SessionManager sessionManager;
    @Inject
    UserCategoryRepository userCategoryRepository;
    private String userEmail;
    private float userBudget;

    private int userId;


    private final List<String> ALL_CATEGORIES = Arrays.asList(
            "Alimentation", "Restaurants", "Shopping", "Carburant", "Loisirs",
            "Téléphone", "Internet", "Factures", "Crédit", "Maison",
            "Voyage", "Santé", "Transport", "Assurance", "Éducation"
    );
    private List<String> userCategories = new ArrayList<>();

    private final Set<String> fixedPayments = new HashSet<>(Arrays.asList("Internet", "Téléphone", "Crédit"));
    private final Map<String, Float> FIXED_AMOUNTS = new HashMap<String, Float>() {{
        put("Crédit", 200f);
        put("Internet", 300f);
        put("Téléphone", 100f);
    }};

    private final Map<String, Integer> categoryEncoding = new HashMap<>();
    private final Map<String, CheckBox> checkBoxMap = new HashMap<>();
    private final int[] CATEGORY_COLORS = {
            Color.parseColor("#6a2202"),  // maron
            Color.parseColor("#bc7404"),  // moutarde
            Color.parseColor("#d6a861"), // moutarde2
            Color.parseColor("#844c2c"), // maron2
            Color.parseColor("#d6bcaa"),  // maron3
            Color.parseColor("#6200EE"),  // primary_color
            Color.parseColor("#757575"),  // secondary_text
            Color.parseColor("#6a2202"),  // maron (répété)
            Color.parseColor("#bc7404"),  // moutarde (répété)
            Color.parseColor("#844c2c")   // maron2 (répété)
    };

    private Map<String, Float> currentAllocations = new HashMap<>();
    private Map<String, SeekBar> seekBarMap = new HashMap<>();
    private TextView remainingBudgetText;
    private float totalBudget;
    private float remainingBudget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse);
        userId    = sessionManager.getUserId();
        userEmail = sessionManager.getUserEmail();
        initializeViews();
        loadUserBudget();
        loadUserCategories();
        setupDynamicCategories();
        setupPredictButton();
        remainingBudgetText = findViewById(R.id.remainingBudgetText);
        setupNavbar();
        BottomNav.setup(this);
    }

    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                Toast.makeText(AnalyseActivity.this,
                        "Refreshing...",
                        Toast.LENGTH_SHORT).show();

                resetUI();
            }
            @Override
            public void onLogoutClicked() {
                sessionManager.clearSession();

                Intent intent = new Intent(AnalyseActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

        });
    }
    private void loadUserCategories() {
        int userId = sessionManager.getUserId();
        List<UserCategory> userCats = userCategoryRepository.getUserCategoriesByUser(userId);

        // Créer une liste unique de catégories
        Set<String> uniqueCategories = new HashSet<>();
        for (UserCategory uc : userCats) {
            // Vous aurez besoin d'une méthode pour récupérer le nom de la catégorie à partir de l'ID
            String categoryName = getCategoryNameById(uc.getIdCategory());
            if (categoryName != null) {
                uniqueCategories.add(categoryName);
            }
        }

        userCategories = new ArrayList<>(uniqueCategories);
        setupDynamicCategories();
    }

    private String getCategoryNameById(int categoryId) {
        // Implémentez cette méthode pour récupérer le nom de la catégorie depuis la base de données
        // Par exemple :
        Realm realm = Realm.getDefaultInstance();
        Category category = realm.where(Category.class)
                .equalTo("idCategory", categoryId)
                .findFirst();
        String name = category != null ? category.getCategoryName() : null;

        return name;
    }
    private void updateDetailedResults(Map<String, Float> allocations) {
        detailedResultsContainer.removeAllViews();
        seekBarMap.clear();
        currentAllocations = new HashMap<>(allocations);
        totalBudget = allocations.values().stream().reduce(0f, Float::sum);
        remainingBudget = totalBudget;

        // Récupérer les catégories fixes
        int userId = sessionManager.getUserId();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UserCategory> fixedCategories = realm.where(UserCategory.class)
                .equalTo("idUser", userId)
                .equalTo("isFixed", true)
                .findAll();

        Set<String> fixedCategoryNames = new HashSet<>();
        for (UserCategory uc : fixedCategories) {
            Category cat = realm.where(Category.class)
                    .equalTo("idCategory", uc.getIdCategory())
                    .findFirst();
            if (cat != null) {
                fixedCategoryNames.add(cat.getCategoryName());
            }
        }
        realm.close();

        int index = 0;
        for (Map.Entry<String, Float> entry : allocations.entrySet()) {
            String name = entry.getKey();
            float amount = entry.getValue();
            boolean isFixed = fixedCategoryNames.contains(name);
            int color = CATEGORY_COLORS[index++ % CATEGORY_COLORS.length];

            // Création du conteneur principal
            LinearLayout rowContainer = new LinearLayout(this);
            rowContainer.setOrientation(LinearLayout.VERTICAL);
            rowContainer.setPadding(0, 8, 0, 8);

            // Ligne supérieure (nom et montant)
            LinearLayout topRow = new LinearLayout(this);
            topRow.setOrientation(LinearLayout.HORIZONTAL);

            View colorIndicator = new View(this);
            colorIndicator.setLayoutParams(new LinearLayout.LayoutParams(24, 24));
            colorIndicator.setBackgroundColor(color);

            TextView nameText = new TextView(this);
            nameText.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            nameText.setText(name + (isFixed ? " (fixe)" : ""));
            nameText.setTextColor(isFixed ? Color.BLUE : Color.BLACK);

            TextView amountText = new TextView(this);
            amountText.setText(String.format("%.2f DH", amount));
            amountText.setTextColor(isFixed ? Color.BLUE : Color.BLACK);

            topRow.addView(colorIndicator);
            topRow.addView(nameText);
            topRow.addView(amountText);

            // SeekBar seulement pour les catégories non fixes
            if (!isFixed) {
                SeekBar seekBar = new SeekBar(this);
                seekBar.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                seekBar.setMax(200);
                seekBar.setProgress(100);
                seekBar.setTag(name);

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            String category = (String) seekBar.getTag();
                            float originalAmount = allocations.get(category);
                            float newAmount = originalAmount * progress / 100f;

                            amountText.setText(String.format("%.2f DH", newAmount));
                            currentAllocations.put(category, newAmount);
                            recalculateRemainingBudget();
                        }
                    }

                    @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override public void onStopTrackingTouch(SeekBar seekBar) {}
                });

                rowContainer.addView(seekBar);
                seekBarMap.put(name, seekBar);
            }

            rowContainer.addView(topRow);
            detailedResultsContainer.addView(rowContainer);
        }

        // Ajout du bouton de sauvegarde
        Button saveButton = new Button(this);
        saveButton.setText("Sauvegarder les modifications");
        saveButton.setBackgroundColor(ContextCompat.getColor(this, R.color.maron));
        saveButton.setTextColor(Color.WHITE);
        saveButton.setOnClickListener(v -> saveModifiedPredictions(currentAllocations));
        detailedResultsContainer.addView(saveButton);

        // Budget restant
        remainingBudgetText.setText(String.format("Budget restant: %.2f DH", remainingBudget));
        remainingBudgetText.setTextColor(remainingBudget < 0 ? Color.RED : Color.parseColor("#6a2202"));
        remainingBudgetText.setVisibility(View.VISIBLE);
    }

    private void recalculateRemainingBudget() {
        float totalAllocated = currentAllocations.values().stream().reduce(0f, Float::sum);
        remainingBudget = totalBudget - totalAllocated;
        remainingBudgetText.setText(String.format("Budget restant: %.2f DH", remainingBudget));

        if (remainingBudget < 0) {
            remainingBudgetText.setTextColor(Color.RED);
        } else {
            remainingBudgetText.setTextColor(Color.parseColor("#6a2202"));
        }
    }
    private void initializeViews() {
        budgetEditText = findViewById(R.id.budgetEditText);
        predictButton = findViewById(R.id.predictButton);
        totalBudgetText = findViewById(R.id.totalBudgetText);
        recommendationText = findViewById(R.id.recommendationText);
        visualBudgetContainer = findViewById(R.id.visualBudgetContainer);
        detailedResultsContainer = findViewById(R.id.detailedResultsContainer);
        loadingIndicator = findViewById(R.id.loadingIndicator);
    }

    private void loadUserBudget() {
        Realm realm = Realm.getDefaultInstance();
        try {
            int userId = sessionManager.getUserId();
            User user = realm.where(User.class)
                    .equalTo("idUser", userId)
                    .findFirst();
            if (user != null) {
                userBudget = (float) user.getMonthlyBudget();
                budgetEditText.setText(String.format(Locale.getDefault(), "%.2f", userBudget));
                budgetEditText.setEnabled(false);
            } else {
                Toast.makeText(this, "Impossible de charger votre budget.", Toast.LENGTH_LONG).show();
            }
        } finally {
            realm.close();
        }
    }

    private void setupDynamicCategories() {
        GridLayout categoryGrid = findViewById(R.id.categoriesGrid);
        categoryGrid.removeAllViews();
        categoryGrid.setColumnCount(2);

        int encoding = 0;
        for (String category : userCategories) {
            CheckBox cb = new CheckBox(this);
            cb.setText(category);
            cb.setTextColor(Color.parseColor("#FF000000")); // noir
            cb.setButtonTintList(getResources().getColorStateList(R.color.moutarde, null));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            cb.setLayoutParams(params);

            categoryEncoding.put(category, encoding++);
            checkBoxMap.put(category, cb);
            categoryGrid.addView(cb);
        }
    }

    private void setupPredictButton() {
        predictButton.setOnClickListener(view -> {
            try {
                String budgetText = budgetEditText.getText().toString().trim();
                if (budgetText.isEmpty()) {
                    Toast.makeText(this, "Veuillez entrer un budget", Toast.LENGTH_SHORT).show();
                    return;
                }

                float totalBudget = Float.parseFloat(budgetText);
                if (totalBudget <= 0) {
                    Toast.makeText(this, "Le budget doit être supérieur à 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<String> selectedCategories = new ArrayList<>();
                for (Map.Entry<String, CheckBox> entry : checkBoxMap.entrySet()) {
                    if (entry.getValue().isChecked()) {
                        selectedCategories.add(entry.getKey());
                    }
                }

                if (selectedCategories.isEmpty()) {
                    Toast.makeText(this, "Veuillez sélectionner au moins une catégorie", Toast.LENGTH_SHORT).show();
                    return;
                }

                loadingIndicator.setVisibility(View.VISIBLE);
                predictButton.setEnabled(false);

                new Thread(() -> {
                    runPrediction(totalBudget, selectedCategories);
                    runOnUiThread(() -> {
                        loadingIndicator.setVisibility(View.GONE);
                        predictButton.setEnabled(true);
                    });
                }).start();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Format de budget invalide", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }

    private  void runPrediction(float totalBudget, List<String> selectedCategories) {
        try {
            // 1. Récupérer les catégories fixes depuis la base de données
            Map<String, Float> fixedAllocations = new HashMap<>();
            float fixedTotal = 0f;

            // Charger les catégories fixes de l'utilisateur
            int userId = sessionManager.getUserId();
            Realm realm = Realm.getDefaultInstance();
            RealmResults<UserCategory> fixedCategories = realm.where(UserCategory.class)
                    .equalTo("idUser", userId)
                    .equalTo("isFixed", true)
                    .findAll();

            for (UserCategory uc : fixedCategories) {
                Category category = realm.where(Category.class)
                        .equalTo("idCategory", uc.getIdCategory())
                        .findFirst();
                if (category != null && selectedCategories.contains(category.getCategoryName())) {
                    float finalBudget = (float) uc.getFinalBudget();
                    fixedAllocations.put(category.getCategoryName(), finalBudget);
                    fixedTotal += finalBudget;

                }
            }
            realm.close();

            // 2. Calculer le budget variable restant
            float variableBudget = totalBudget - fixedTotal;
            if (variableBudget < 0) {
                final float  finalfixedtotal=fixedTotal;
                runOnUiThread(() -> {
                    Toast.makeText(this,
                            "Budget insuffisant pour les paiements fixes (" + finalfixedtotal + " DH)",
                            Toast.LENGTH_LONG).show();
                });
                return;
            }

            // 3. Filtrer les catégories variables (non fixes)
            List<String> variableCategories = new ArrayList<>();
            for (String category : selectedCategories) {
                if (!fixedAllocations.containsKey(category)) {
                    variableCategories.add(category);
                }
            }

            // 4. Prédiction seulement pour les catégories variables
            TFLitePredictor predictor = new TFLitePredictor(this);
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int dow = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
            int account = 1;

            Map<String, Float> predictionRaw = new HashMap<>();
            float totalPred = 0f;

            for (String cat : variableCategories) {
                int encoding = categoryEncoding.get(cat);
                float pred = predictor.predictAmount(encoding, month, dow, account);
                predictionRaw.put(cat, pred);
                totalPred += pred;
            }

            // 5. Répartition proportionnelle du budget variable
            Map<String, Float> variableAllocations = new LinkedHashMap<>();
            if (totalPred > 0) { // Éviter division par zéro
                for (Map.Entry<String, Float> entry : predictionRaw.entrySet()) {
                    float amount = (entry.getValue() / totalPred) * variableBudget;
                    variableAllocations.put(entry.getKey(), amount);
                }
            }

            // 6. Combiner les résultats (fixes + variables)
            Map<String, Float> finalAllocations = new LinkedHashMap<>();
            finalAllocations.putAll(fixedAllocations);
            finalAllocations.putAll(variableAllocations);
            final float fixedtotalfinal =fixedTotal;
            runOnUiThread(() -> {
                updateVisualBudget(finalAllocations);
                updateDetailedResults(finalAllocations);
                totalBudgetText.setText(String.format("Total: %.2f DH (dont %.2f DH fixes)",
                        totalBudget, fixedtotalfinal));
                updateRecommendations(finalAllocations, totalBudget);
            });

        } catch (Exception e) {
            runOnUiThread(() -> {
                Toast.makeText(this, "Erreur de prédiction", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            });
        }
    }
    private void updateVisualBudget(Map<String, Float> allocations) {
        visualBudgetContainer.removeAllViews();
        float total = allocations.values().stream().reduce(0f, Float::sum);
        int index = 0;

        for (Map.Entry<String, Float> entry : allocations.entrySet()) {
            float pct = entry.getValue() / total;
            View segment = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = pct;
            segment.setLayoutParams(params);
            segment.setBackgroundColor(CATEGORY_COLORS[index++ % CATEGORY_COLORS.length]);
            visualBudgetContainer.addView(segment);
        }
    }
    private void saveModifiedPredictions(Map<String, Float> allocations) {
        int userId = sessionManager.getUserId();
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(r -> {
            // D'abord marquer toutes les catégories sélectionnées comme non fixes
            for (String categoryName : allocations.keySet()) {
                Category category = r.where(Category.class)
                        .equalTo("categoryName", categoryName)
                        .findFirst();

                if (category != null) {
                    UserCategory uc = r.where(UserCategory.class)
                            .equalTo("idUser", userId)
                            .equalTo("idCategory", category.getIdCategory())
                            .findFirst();

                    if (uc != null) {
                        uc.setFixed(false);
                    }
                }
            }

            // Puis sauvegarder toutes les allocations
            for (Map.Entry<String, Float> entry : allocations.entrySet()) {
                String categoryName = entry.getKey();
                float amount = entry.getValue();
                boolean isFixed = fixedPayments.contains(categoryName);

                Category category = r.where(Category.class)
                        .equalTo("categoryName", categoryName)
                        .findFirst();

                if (category != null) {
                    UserCategory uc = r.where(UserCategory.class)
                            .equalTo("idUser", userId)
                            .equalTo("idCategory", category.getIdCategory())
                            .findFirst();

                    if (uc == null) {
                        uc = r.createObject(UserCategory.class, generateId(r));
                        uc.setIdUser(userId);
                        uc.setIdCategory(category.getIdCategory());
                        // Définir l'option par défaut si nécessaire
                    }

                    uc.setRecommendedBudget(amount);
                    uc.setFinalBudget(amount);
                    uc.setFixed(isFixed); // Conserve le statut fixe pour les paiements fixes
                }
            }
        }, () -> {
            Toast.makeText(this, "Modifications sauvegardées", Toast.LENGTH_SHORT).show();
        }, error -> {
            Toast.makeText(this, "Erreur de sauvegarde", Toast.LENGTH_SHORT).show();
            Log.e("SAVE_ERROR", error.getMessage(), error);
        });
    }

    private int generateId(Realm realm) {
        Number maxId = realm.where(UserCategory.class).max("idUserCategory");
        return (maxId != null) ? maxId.intValue() + 1 : 1;
    }

    private void updateRecommendations(Map<String, Float> allocations, float totalBudget) {
        String topCategory = null;
        float max = 0;
        for (Map.Entry<String, Float> entry : allocations.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topCategory = entry.getKey();
            }
        }

        float percent = (max / totalBudget) * 100;
        StringBuilder msg = new StringBuilder();

        if (percent > 50) {
            msg.append("⚠ Plus de 50% de votre budget est alloué à ").append(topCategory).append(". Considérez une meilleure répartition.");
        } else if (percent > 30) {
            msg.append("📊 Votre budget est majoritairement utilisé pour ").append(topCategory).append(". Vérifiez si c'est optimal.");
        } else {
            msg.append("✅ Votre budget est bien réparti entre les différentes catégories.");
        }

        recommendationText.setText(msg.toString());
        recommendationText.setTextColor(Color.parseColor("#6a2202")); // maron
    }

    /**
     * Remet l'écran à l'état initial :
     * - vide budget, décoche cases
     * - efface graphiques et résultats
     * - remet boutons / loaders à l'état de départ
     */
    private void resetUI() {
        // Vide le champ budget
        budgetEditText.setText("");

        // Décoche toutes les cases
        for (CheckBox cb : checkBoxMap.values()) {
            cb.setChecked(false);
        }

        // Efface la représentation visuelle
        visualBudgetContainer.removeAllViews();
        // Efface les détails et les sliders
        detailedResultsContainer.removeAllViews();

        // Vide / masque tous les TextView de résultat
        totalBudgetText.setText("");
        remainingBudgetText.setVisibility(View.GONE);
        recommendationText.setText("");

        // Réactive le bouton et masque le loader
        predictButton.setEnabled(true);
        loadingIndicator.setVisibility(View.GONE);
    }

}