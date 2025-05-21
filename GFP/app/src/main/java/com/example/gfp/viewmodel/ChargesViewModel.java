// com/example/gfp/viewmodel/ChargesViewModel.java
package com.example.gfp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gfp.data.model.Category;
import com.example.gfp.data.model.Option;
import com.example.gfp.data.model.UserCategory;
import com.example.gfp.data.repository.UserCategoryRepository;
import com.example.gfp.data.session.SessionManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.realm.Realm;

@HiltViewModel
public class ChargesViewModel extends AndroidViewModel {
    private final MutableLiveData<List<CategoryDisplay>> categoriesLiveData =
            new MutableLiveData<>();
    private final MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>(); // Nouveau
    private final UserCategoryRepository repo;
    private final SessionManager sessionManager;
    private final Realm realm;

    @Inject
    public ChargesViewModel(@NonNull Application application,
                            UserCategoryRepository repo,
                            SessionManager sessionManager) {
        super(application);
        this.repo = repo;
        this.sessionManager = sessionManager;
        this.realm = Realm.getDefaultInstance();
        load();
    }
    public LiveData<Boolean> getSaveSuccess() {
        return saveSuccess;
    }
    private void load() {
        List<UserCategory> rows = repo.getUserCategories();

        Map<Integer, List<UserCategory>> byCat = new LinkedHashMap<>();
        for (UserCategory uc : rows) {
            byCat.computeIfAbsent(uc.getIdCategory(), k -> new ArrayList<>())
                    .add(uc);
        }

        List<CategoryDisplay> out = new ArrayList<>();
        for (Map.Entry<Integer, List<UserCategory>> e : byCat.entrySet()) {
            int catId = e.getKey();
            Category cat = realm.where(Category.class)
                    .equalTo("idCategory", catId)
                    .findFirst();
            String name  = cat != null ? cat.getCategoryName() : "Unknown";
            String emoji = getEmojiFor(name);

            List<OptionDisplay> opts = new ArrayList<>();
            for (UserCategory uc : e.getValue()) {
                Option opt = realm.where(Option.class)
                        .equalTo("idOption", uc.getIdOption())
                        .findFirst();
                String optName = opt != null ? opt.getOptionName() : "—";
                opts.add(new OptionDisplay(
                        uc.getIdOption(),
                        optName,
                        uc.getFinalBudget(),
                        uc.isFixed()
                ));
            }
            out.add(new CategoryDisplay(catId, name, emoji, opts));
        }
        categoriesLiveData.setValue(out);
    }

    public LiveData<List<CategoryDisplay>> getCategoriesLiveData() {
        return categoriesLiveData;
    }

    public void saveCategoryDisplays(List<CategoryDisplay> list) {
        realm.executeTransactionAsync(r -> {
            int userId = sessionManager.getUserId();
            for (CategoryDisplay cd : list) {
                for (OptionDisplay od : cd.options) {
                    UserCategory uc = r.where(UserCategory.class)
                            .equalTo("idUser", userId)
                            .equalTo("idCategory", cd.categoryId)
                            .equalTo("idOption", od.optionId)
                            .findFirst();
                    if (uc != null) {
                        uc.setFinalBudget(od.budget);
                        uc.setFixed(od.isFixed);
                    }
                }
            }
        }, () -> {
            // Succès
            saveSuccess.postValue(true);
        }, error -> {
            // Erreur
            saveSuccess.postValue(false);
        });
    }

    private String getEmojiFor(String name) {
        switch (name.toLowerCase()) {
            case "food":          return "🍔";
            case "restaurants":   return "🍽️";
            case "shopping":      return "🛍️";
            case "fuel":          return "⛽";
            case "leisure":       return "🎭";
            case "phone":         return "📱";
            case "internet":      return "🌐";
            case "bills":         return "💰";
            case "credit":        return "💳";
            case "home":          return "🏠";
            case "travel":        return "✈️";
            case "health":        return "🏥";
            case "transportation":return "🚌";
            case "insurance":     return "🔒";
            case "education":     return "🎓";
            default:              return "🏠";
        }
    }

    @Override
    protected void onCleared() {
        realm.close();
        super.onCleared();
    }
}
