package com.example.gfp.data.repository;

import com.example.gfp.data.model.Category;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.realm.Realm;

@Singleton
public class CategoryRepository {
    private final Realm realm;

    @Inject
    public CategoryRepository() {
        realm = Realm.getDefaultInstance();
    }

    public String getCategoryName(int categoryId) {
        Category category = realm.where(Category.class)
                .equalTo("idCategory", categoryId)
                .findFirst();
        return category != null ? category.getCategoryName() : "Inconnue";
    }
}