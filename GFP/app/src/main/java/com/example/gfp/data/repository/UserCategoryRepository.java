// com/example/gfp/data/repository/UserCategoryRepository.java
package com.example.gfp.data.repository;

import com.example.gfp.data.model.UserCategory;
import com.example.gfp.data.session.SessionManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

@Singleton
public class UserCategoryRepository {
    private final SessionManager sessionManager;

    @Inject
    public UserCategoryRepository(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    public UserCategory getById(int idUserCategory) {
        Realm realm = Realm.getDefaultInstance();
        try {
            UserCategory userCategory = realm.where(UserCategory.class)
                    .equalTo("idUserCategory", idUserCategory)
                    .findFirst();
            return userCategory != null ? realm.copyFromRealm(userCategory) : null;
        } finally {
            realm.close();
        }
    }

    /**
     * Récupère toutes les entrées UserCategory pour l'utilisateur en session.
     */
    public List<UserCategory> getUserCategories() {
        int userId = sessionManager.getUserId();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UserCategory> results = realm.where(UserCategory.class)
                .equalTo("idUser", userId)
                .findAll();
        List<UserCategory> list = realm.copyFromRealm(results);
        realm.close();
        return list;
    }
    public List<UserCategory> getUserCategoriesByUser(int userId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UserCategory> results = realm.where(UserCategory.class)
                .equalTo("idUser", userId)
                .findAll();
        List<UserCategory> list = realm.copyFromRealm(results);
        realm.close();
        return list;
    }
}
