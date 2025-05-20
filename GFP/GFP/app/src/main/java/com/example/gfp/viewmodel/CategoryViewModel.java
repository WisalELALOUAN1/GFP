package com.example.gfp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gfp.data.model.Category;
import com.example.gfp.data.model.Option;
import com.example.gfp.data.model.UserCategory;
import com.example.gfp.data.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

@HiltViewModel
public class CategoryViewModel extends AndroidViewModel {

    private final Realm realm;
    private final SessionManager sessionManager;

    // LiveData pour l'UI
    private final MutableLiveData<List<Category>> categoriesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Category> currentCategoryLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    private List<Category> categories;
    private int currentCategoryIndex = 0;

    /**
     * Hilt va injecter l’Application et le SessionManager
     * (singleton fourni par votre module Hilt)
     */
    @Inject
    public CategoryViewModel(@NonNull Application application,
                             SessionManager sessionManager) {
        super(application);
        this.sessionManager = sessionManager;
        realm = Realm.getDefaultInstance();

        // 1) Initialisation en base des catégories (si nécessaire)
        initializeCategories();

        // 2) Chargement pour l’UI
        loadCategories();
    }

    /** Initialise en base la liste des catégories et de leurs options */
    public void initializeCategories() {
        realm.executeTransaction(r -> {
            RealmResults<Category> existing = r.where(Category.class).findAll();
            if (existing.isEmpty()) {
                List<String[]> defs = new ArrayList<>();
                defs.add(new String[]{"Food", "Groceries", "Organic food"});
                defs.add(new String[]{"Restaurants", "Fine dining", "Casual dining", "Fast food", "Take-out"});
                defs.add(new String[]{"Shopping", "Clothes", "Electronics", "Home goods", "Online shopping"});
                defs.add(new String[]{"Fuel", "Gasoline", "Electric charging"});
                defs.add(new String[]{"Leisure", "Entertainment", "Movies", "Hobbies", "Sports"});
                defs.add(new String[]{"Phone", "Mobile bill", "Apps"});
                defs.add(new String[]{"Internet", "Home Internet", "Mobile Internet"});
                defs.add(new String[]{"Bills", "Electricity", "Water"});
                defs.add(new String[]{"Credit", "Credit card payments", "Loan repayments"});
                defs.add(new String[]{"Home", "Rent", "Mortgage", "Home maintenance"});
                defs.add(new String[]{"Travel", "Vacation", "Business trips", "Flights", "Accommodation"});
                defs.add(new String[]{"Health", "Medical expenses", "Medications"});
                defs.add(new String[]{"Transportation", "Public transport", "Car payments", "Maintenance", "Parking"});
                defs.add(new String[]{"Insurance", "Health insurance", "Life insurance", "Car insurance", "Home insurance"});
                defs.add(new String[]{"Education", "Tuition fees", "Books", "Courses", "Online education"});

                int categoryId = 1;
                for (String[] def : defs) {
                    Category cat = new Category();
                    cat.setIdCategory(categoryId);
                    cat.setCategoryName(def[0]);

                    RealmList<Option> opts = new RealmList<>();
                    for (int i = 1; i < def.length; i++) {
                        Option opt = new Option();
                        int optionId = categoryId * 100 + i;
                        opt.setIdOption(optionId);
                        opt.setOptionName(def[i]);
                        opts.add(opt);
                    }
                    cat.setOptions(opts);
                    r.insertOrUpdate(cat);
                    categoryId++;
                }
            }
        });
    }

    /** Charge en mémoire et notifie l’UI */
    private void loadCategories() {
        RealmResults<Category> realmCats = realm.where(Category.class).findAll();
        categories = realm.copyFromRealm(realmCats);
        categoriesLiveData.setValue(categories);

        if (!categories.isEmpty()) {
            updateCurrentCategory(0);
        }
    }

    /** Met à jour la catégorie courante et la progression */
    private void updateCurrentCategory(int index) {
        currentCategoryIndex = index;
        currentCategoryLiveData.setValue(categories.get(index));
        int prog = ((index + 1) * 100) / categories.size();
        progressLiveData.setValue(prog);
    }

    /** Passe à la catégorie suivante (false si fin) */
    public boolean nextCategory() {
        if (currentCategoryIndex < categories.size() - 1) {
            updateCurrentCategory(currentCategoryIndex + 1);
            return true;
        }
        return false;
    }

    /** Revient à la catégorie précédente (false si début) */
    public boolean previousCategory() {
        if (currentCategoryIndex > 0) {
            updateCurrentCategory(currentCategoryIndex - 1);
            return true;
        }
        return false;
    }

    /**
     * Sauvegarde la sélection utilisateur en créant un UserCategory.
     * Ne fait rien si optionId == 0.
     * Utilise l’ID de l’utilisateur stocké en session.
     */
    public void saveUserSelection(int categoryId, int optionId) {
        if (optionId == 0) return;

        int userId = sessionManager.getUserId();  // ← ici
        realm.executeTransaction(r -> {
            Number maxId = r.where(UserCategory.class).max("idUserCategory");
            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;

            UserCategory uc = r.createObject(UserCategory.class, nextId);
            uc.setIdUser(userId);                // ← ici
            uc.setIdCategory(categoryId);
            uc.setIdOption(optionId);
            uc.setRecommendedBudget(0.0);
            uc.setFixed(false);
            uc.setFinalBudget(0.0);
        });
    }

    // --- Getters LiveData pour l’UI ---
    public LiveData<List<Category>> getCategoriesLiveData() {
        return categoriesLiveData;
    }
    public LiveData<Category> getCurrentCategoryLiveData() {
        return currentCategoryLiveData;
    }
    public LiveData<Integer> getProgressLiveData() {
        return progressLiveData;
    }
    public int getTotalCategories() {
        return categories != null ? categories.size() : 0;
    }
    public int getCurrentCategoryIndex() {
        return currentCategoryIndex;
    }

    @Override
    protected void onCleared() {
        realm.close();
        super.onCleared();
    }
}
