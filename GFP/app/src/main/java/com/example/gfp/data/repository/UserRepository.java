package com.example.gfp.data.repository;

import android.util.Log;
import com.example.gfp.data.model.User;
import io.realm.Realm;

public class UserRepository {

    private static final String TAG_SAVE   = "REALM_SAVE";
    private static final String TAG_UPDATE = "REALM_UPDATE";
    private static final String TAG_GET    = "REALM_GET";

    public UserRepository() { /* pas d’instance Realm ici */ }

    public boolean saveUserProfile(User user) {
        if (user == null) return false;
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(r -> {
            if (user.getId() == 0) {
                Number max = r.where(User.class).max("id");
                user.setId(max == null ? 1 : max.intValue() + 1);
            }
            r.insertOrUpdate(user);
            Log.d(TAG_SAVE, "Utilisateur sauvegardé: " + user.getEmail());
        }, () -> {
            Log.d(TAG_SAVE, "Transaction réussie");
            realm.close();
        }, err -> {
            Log.e(TAG_SAVE, "Erreur Realm: " + err.getMessage(), err);
            realm.close();
        });
        return true;
    }

    public User getUserByFirebaseUid(String uid) {
        Realm realm = Realm.getDefaultInstance();
        try {
            User u = realm.where(User.class)
                    .equalTo("userName", uid)
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
                    .equalTo("id", updated.getId())
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
}
