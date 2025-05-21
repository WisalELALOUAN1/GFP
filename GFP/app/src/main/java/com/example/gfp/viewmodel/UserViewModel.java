package com.example.gfp.viewmodel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.User;
import com.example.gfp.data.repository.AuthRepository;
import com.example.gfp.data.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.realm.Realm;
import io.realm.RealmList;
@HiltViewModel
public class UserViewModel extends ViewModel {

    private final AuthRepository authRepo;
    private final UserRepository userRepo;
    private String currentUserEmail;
    private MutableLiveData<List<Goal>> goalsLiveData = new MutableLiveData<>();
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    @Inject
    public UserViewModel(AuthRepository authRepo, UserRepository userRepo) {
        this.authRepo = authRepo;
        this.userRepo = userRepo;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void login(String email, String pwd, Context context, AuthRepository.AuthCallback cb) {
        if (isNetworkAvailable(context)) {
            authRepo.login(email, pwd, new AuthRepository.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser fbUser) {
                    handleLoginSuccess(fbUser, cb);
                }

                @Override
                public void onFailure(String message) {
                    tryLocalLogin(email, pwd, cb);
                }
            });
        } else {
            tryLocalLogin(email, pwd, cb);
        }
    }
    private void handleLoginSuccess(FirebaseUser fbUser, AuthRepository.AuthCallback cb) {
        setCurrentUserEmail(fbUser.getEmail());
        saveUserToRealm(fbUser);
        cb.onSuccess(fbUser);
    }

    private void saveUserToRealm(FirebaseUser fbUser) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(r -> {
                User stored = r.where(User.class)
                        .equalTo("email", fbUser.getEmail())
                        .findFirst();

                if (stored == null) {
                    Number maxId = r.where(User.class).max("idUser");
                    int nextId = maxId == null ? 1 : maxId.intValue() + 1;
                    User newUser = r.createObject(User.class, nextId);
                    newUser.setEmail(fbUser.getEmail());
                    newUser.setLastName(fbUser.getDisplayName() != null ?
                            fbUser.getDisplayName() : "");
                    newUser.setFirstName("");
                    newUser.setPassword("");
                    newUser.setCurrency("MAD");
                    newUser.setMonthlyBudget(0);
                    newUser.setFirstLogin(1);
                }
            });
        } finally {
            System.out.println("getUserByFireBaeseId");
        }
    }
    private void tryLocalLogin(String email, String pwd, AuthRepository.AuthCallback cb) {
        Realm realm = Realm.getDefaultInstance();
        try {
            User user = realm.where(User.class)
                    .equalTo("email", email)
                    .equalTo("password", pwd)
                    .findFirst();

            if (user != null) {
                setCurrentUserEmail(user.getEmail());
                cb.onSuccess(null);
            } else {
                cb.onFailure("Identifiants incorrects ou utilisateur inexistant");
            }
        } finally {
            System.out.println("getUserByFireBaeseId");
        }
    }
    public LiveData<List<Goal>> getGoalsLiveData() {
        return goalsLiveData;
    }
    public void addGoalToUser(User user, Goal goal) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            if (user.getGoals() == null) {
                user.setGoals(new RealmList<>());
            }
            user.getGoals().add(goal);
            r.insertOrUpdate(user);
            Log.d("UserViewModel", "Objectif ajouté : " + goal.getDescription());
        });
        goalsLiveData.setValue(user.getGoals());
    }
    public void register(String email, String pwd, AuthRepository.AuthCallback cb) {
        authRepo.register(email, pwd, cb);
    }
    public void signInWithGoogle(String idToken, AuthRepository.AuthCallback cb) {
        authRepo.signInWithGoogle(idToken, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser fbUser) {
                setCurrentUserEmail(fbUser.getEmail());
                saveIfMissing(fbUser);
                cb.onSuccess(fbUser);
            }

            @Override
            public void onFailure(String message) {
                cb.onFailure(message);
            }
        });
    }
    public boolean saveUserProfile(User user) {
        return userRepo.saveUserProfile(user);
    }
    public User getUserByEmail(String email) {
        return userRepo.getUserByFirebaseUid(email);
    }
    public void forgotPassword(String email, AuthRepository.VoidCallback cb) {
        authRepo.resetPassword(email, cb);
    }

    public void saveIfMissing(FirebaseUser fUser) {
        if (fUser == null) return;
        if (userRepo.getUserByFirebaseUid(fUser.getEmail()) == null) {
            User u = new User();
            u.setEmail(fUser.getEmail());
            u.setLastName(fUser.getDisplayName() != null
                    ? fUser.getDisplayName() : "");
            u.setFirstName("");
            u.setPassword("");
            u.setCurrency("MAD");
            u.setMonthlyBudget(0);
            userRepo.saveUserProfile(u);
        }
    }

    public Goal getGoalById(int goalId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Goal.class).equalTo("idObj", goalId).findFirst();
    }
}
