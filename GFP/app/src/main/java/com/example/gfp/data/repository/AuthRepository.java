package com.example.gfp.data.repository;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthRepository {
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(String message);
    }

    // Email / mot de passe
    public void login(String email, String password, AuthCallback cb) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess(r.getUser()))
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }
    public void register(String email, String password, AuthCallback cb) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> cb.onSuccess(r.getUser()))
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }
    public void logout() {
        auth.signOut();
    }

    // Google Sign-In
    public void signInWithGoogle(String idToken, AuthCallback cb) {
        AuthCredential cred = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(cred)
                .addOnSuccessListener(r -> cb.onSuccess(r.getUser()))
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }

    // Reset password
    public interface VoidCallback {
        void onSuccess();
        void onFailure(String msg);
    }
    public void resetPassword(String email, VoidCallback cb) {
        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(v -> cb.onSuccess())
                .addOnFailureListener(e -> cb.onFailure(e.getMessage()));
    }
}
