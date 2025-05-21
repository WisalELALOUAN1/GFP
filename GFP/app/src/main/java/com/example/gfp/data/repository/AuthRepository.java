package com.example.gfp.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Fournit les méthodes d'authentification Firebase (e-mail/mdp, Google, reset password).
 * Annoté @Singleton pour n’avoir qu’une instance et @Inject pour permettre à Hilt de le créer.
 */
@Singleton
public class AuthRepository {
    private final Context context;

    private final FirebaseAuth auth;

    /** Hilt utilisera ce constructeur pour fournir une instance de AuthRepository */
    @Inject
    public AuthRepository(Context context) {
        this.auth = FirebaseAuth.getInstance();
        this.context = context;
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(String message);
    }

    /** Connexion e-mail / mot de passe */
    public void login(String email, String password, AuthCallback cb) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess(r.getUser()))
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }

    /** Inscription e-mail / mot de passe */
    public void register(String email, String password, AuthCallback cb) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess(r.getUser()))
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }

    /** Récupère l’utilisateur Firebase connecté actuellement */
    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    /** Déconnexion */
    public void logout() {
        auth.signOut();
    }

    /** Connexion via Google One-Tap / OAuth */
    public void signInWithGoogle(String idToken, AuthCallback cb) {
        AuthCredential cred = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(cred)
                .addOnSuccessListener(r -> cb.onSuccess(r.getUser()))
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }

    public interface VoidCallback {
        void onSuccess();
        void onFailure(String msg);
    }

    /** Réinitialisation de mot de passe */
    public void resetPassword(String email, VoidCallback cb) {
        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(v -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }
}
