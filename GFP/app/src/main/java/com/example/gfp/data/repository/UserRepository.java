// com/example/gfp/data/repository/UserRepository.java
package com.example.gfp.data.repository;

import android.util.Log;

import com.example.gfp.data.model.User;
import com.example.gfp.data.session.SessionManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;

@Singleton
public class UserRepository {

    private static final String TAG_SAVE   = "REALM_SAVE";
    private static final String TAG_UPDATE = "REALM_UPDATE";
    private static final String TAG_GET    = "REALM_GET";

    private final SessionManager sessionManager;

    @Inject
    public UserRepository(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Sauvegarde (ou crée) un User en base :
     * - Si idUser == 0 : génère un nouvel ID et firstLogin=1
     * - Insère ou met à jour
     * - À la fin, stocke automatiquement idUser dans la session
     */
    public boolean saveUserProfile(User user) {
        if (user == null) return false;

        Realm realm = Realm.getDefaultInstance();
        // 1) exécution SYNCHRONE
        realm.executeTransaction(r -> {
            if (user.getIdUser() == 0) {
                Number max = r.where(User.class).max("idUser");
                int nextId = (max == null ? 1 : max.intValue() + 1);
                user.setIdUser(nextId);
                user.setFirstLogin(1);
            }
            r.insertOrUpdate(user);
            Log.d(TAG_SAVE, "Utilisateur sauvegardé (sync): " + user.getEmail());
        });
        realm.close();

        // 2) mise à jour immédiate de la session
        sessionManager.setUserId(user.getIdUser());
        Log.d(TAG_SAVE, "Session mise à jour idUser=" + user.getIdUser());

        return true;
    }


    public User getUserByFirebaseUid(String uid) {
        Realm realm = Realm.getDefaultInstance();
        try {
            User u = realm.where(User.class)
                    .equalTo("email", uid)
                    .findFirst();
            Log.d(TAG_GET, u != null
                    ? "Utilisateur trouvé: " + u.getEmail()
                    : "Aucun user pour UID: " + uid);
            return u;
        } finally {
            realm.close();
        }
    }

    public void updateUser(User updated) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            User u = r.where(User.class)
                    .equalTo("idUser", updated.getIdUser())
                    .findFirst();
            if (u != null) {
                u.setLastName(updated.getLastName());
                u.setFirstName(updated.getFirstName());
                u.setCurrency(updated.getCurrency());
                u.setMonthlyBudget(updated.getMonthlyBudget());
                Log.d(TAG_UPDATE, "User mis à jour: " + u.getEmail());
            }
        });
        realm.close();
    }

    /**
     * Met à jour le monthlyBudget pour cet utilisateur.
     */
    public boolean updateMonthlyBudget(int userId, double budget) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(r -> {
                User u = r.where(User.class)
                        .equalTo("idUser", userId)
                        .findFirst();
                if (u != null) {
                    u.setMonthlyBudget(budget);
                    r.insertOrUpdate(u);
                    Log.d(TAG_UPDATE, "Monthly budget mis à jour pour user " + userId);
                }
            });
            return true;
        } catch (Exception e) {
            Log.e(TAG_UPDATE, "Erreur updateMonthlyBudget: " + e.getMessage(), e);
            return false;
        } finally {
            realm.close();
        }
    }
}
