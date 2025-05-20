package com.example.gfp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gfp.MainActivity;
import com.example.gfp.R;
import com.example.gfp.data.model.User;
import com.example.gfp.data.repository.AuthRepository;
import com.example.gfp.viewmodel.UserViewModel;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    private UserViewModel userVM;
    private TextInputEditText etEmail, etPwd;
    private SignInButton btnGoogle;
    private BeginSignInRequest signInReq;
    private SignInClient oneTapClient;
    private ActivityResultLauncher<IntentSenderRequest> launcher;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userVM = new ViewModelProvider(this).get(UserViewModel.class);

        // Find view elements
        etEmail = findViewById(R.id.etEmailLogin);
        etPwd = findViewById(R.id.etPasswordLogin);
        btnGoogle = findViewById(R.id.btnGoogleSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);

        setupOneTap(); // Google One-Tap Sign-In

        // Email / Password Sign-In
        findViewById(R.id.btnSignIn).setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pwd = etPwd.getText().toString();
            if (email.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }
            userVM.login(email, pwd, new AuthRepository.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser u) {
                    Toast.makeText(LoginActivity.this, "Bienvenue " + u.getEmail(), Toast.LENGTH_SHORT).show();
                    startMain();
                }

                @Override
                public void onFailure(String msg) {
                    Toast.makeText(LoginActivity.this, "Erreur: " + msg, Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Sign-Up Redirection
        tvSignUp.setOnClickListener(v ->
                startActivity(new Intent(this, SignUpActivity.class))
        );

        // Google One-Tap Sign-In
        btnGoogle.setOnClickListener(v ->
                oneTapClient.beginSignIn(signInReq)
                        .addOnSuccessListener(res -> {
                            launcher.launch(
                                    new IntentSenderRequest.Builder(res.getPendingIntent().getIntentSender()).build()
                            );
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(this, "One-Tap échoué: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        )
        );
    }

    private void setupOneTap() {
        oneTapClient = Identity.getSignInClient(this);
        signInReq = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                .setSupported(true)
                                .setServerClientId(getString(R.string.default_web_client_id))
                                .setFilterByAuthorizedAccounts(false)
                                .build()
                )
                .build();

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartIntentSenderForResult(),
                result -> {
                    if (result.getResultCode() != RESULT_OK || result.getData() == null) return;
                    try {
                        SignInCredential cr = oneTapClient.getSignInCredentialFromIntent(result.getData());

                        String idToken = cr.getGoogleIdToken();
                        String givenName = cr.getGivenName() != null ? cr.getGivenName() : "";
                        String familyName = cr.getFamilyName() != null ? cr.getFamilyName() : "";

                        if (idToken == null) {
                            Toast.makeText(this, "Pas de token Google", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Firebase Authentication with Google
                        userVM.signInWithGoogle(idToken, new AuthRepository.AuthCallback() {
                            @Override
                            public void onSuccess(FirebaseUser fbUser) {
                                // Create / Update User in Realm
                                Realm realm = Realm.getDefaultInstance();
                                realm.executeTransaction(r -> {
                                    // If the user doesn't exist, create a new one
                                    User stored = r.where(User.class)
                                            .equalTo("userName", fbUser.getEmail())
                                            .findFirst();
                                    if (stored == null) {
                                        Number maxId = r.where(User.class).max("id");
                                        int nextId = maxId == null ? 1 : maxId.intValue() + 1;
                                        User u = r.createObject(User.class, nextId);
                                        u.setEmail(fbUser.getEmail());
                                        u.setFirstName(givenName);
                                        u.setLastName(familyName);
                                        u.setPassword("");           // Not used for Google sign-in
                                        u.setCurrency("MAD");
                                        u.setMonthlyBudget(0);
                                        Log.d("REALM_SAVE", "Nouvel utilisateur GOOG stocké: " + fbUser.getEmail());
                                    }
                                });
                                realm.close();

                                // Proceed to MainActivity
                                startMain();
                            }

                            @Override
                            public void onFailure(String message) {
                                Toast.makeText(LoginActivity.this, "Erreur Google Auth: " + message, Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (ApiException e) {
                        Toast.makeText(this, "OneTap API: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void startMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
