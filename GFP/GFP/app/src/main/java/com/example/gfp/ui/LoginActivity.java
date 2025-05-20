package com.example.gfp.ui;

import static androidx.core.content.ContextCompat.startActivity;

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

import javax.inject.Inject;

import io.realm.Realm;
import com.example.gfp.data.session.SessionManager;
import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    @Inject
    SessionManager session;
    private UserViewModel userVM;
    private TextInputEditText etEmail, etPwd;
    private SignInButton btnGoogle;
    private BeginSignInRequest signInReq;
    private SignInClient oneTapClient;
    private ActivityResultLauncher<IntentSenderRequest> launcher;
    private TextView tvSignUp;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();
        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        setupViews();
        setupOneTap();
    }

    private void setupViews() {
        etEmail = findViewById(R.id.etEmailLogin);
        etPwd = findViewById(R.id.etPasswordLogin);
        btnGoogle = findViewById(R.id.btnGoogleSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);

        // Email/Password Login
        findViewById(R.id.btnSignIn).setOnClickListener(v -> handleEmailLogin());

        // Sign-Up Redirection
        tvSignUp.setOnClickListener(v ->
                startActivity(new Intent(this, SignUpActivity.class))
        );

        // Google Sign-In
        btnGoogle.setOnClickListener(v -> initiateGoogleSignIn());
    }

    private void handleEmailLogin() {
        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString();

        if (email.isEmpty() || pwd.isEmpty()) {
            showToast("Veuillez remplir tous les champs");
            return;
        }

        userVM.login(email, pwd, this, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser u) {
                realm.executeTransaction(r -> {
                    User user = r.where(User.class)
                            .equalTo("email", email)
                            .findFirst();

                    if (user == null) {
                        Number maxId = r.where(User.class).max("idUser");
                        int newId = (maxId == null) ? 1 : maxId.intValue() + 1;

                        user = r.createObject(User.class, newId);
                        user.setEmail(email);
                        user.setFirstLogin(1);
                        user.setCurrency("MAD");
                    }

                    session.setUserId(user.getIdUser());
                    session.setUserEmail(user.getEmail());

                    // Créez une copie finale pour utiliser dans le runOnUiThread
                    final User finalUser = realm.copyFromRealm(user);

                    runOnUiThread(() -> {
                        if (finalUser.getFirstLogin() == 1) {
                            startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                        } else {
                            redirectToMain();
                        }
                        finish();
                    });
                });
            }

            @Override
            public void onFailure(String msg) {
                Log.e("LoginError", "Échec de connexion: " + msg);
                showToast("Échec: " + msg);
            }
        });
    }

    private void initiateGoogleSignIn() {
        oneTapClient.beginSignIn(signInReq)
                .addOnSuccessListener(res -> {
                    launcher.launch(
                            new IntentSenderRequest.Builder(res.getPendingIntent().getIntentSender()).build()
                    );
                })
                .addOnFailureListener(e -> {
                    Log.e("GoogleSignIn", "One-Tap failed: " + e.getMessage());
                    showToast("Échec Google Sign-In: " + e.getMessage());
                });
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
                this::handleGoogleSignInResult
        );
    }

    private void handleGoogleSignInResult(androidx.activity.result.ActivityResult result) {
        if (result.getResultCode() != RESULT_OK || result.getData() == null) {
            showToast("Connexion Google annulée");
            return;
        }

        try {
            SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
            String idToken = credential.getGoogleIdToken();

            if (idToken == null) {
                showToast("Pas de token Google reçu");
                return;
            }

            handleGoogleToken(idToken, credential);
        } catch (ApiException e) {
            Log.e("GoogleSignIn", "API Error: " + e.getMessage());
            showToast("Erreur Google Sign-In");
        }
    }

    private void handleGoogleToken(String idToken, SignInCredential credential) {
        userVM.signInWithGoogle(idToken, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser fbUser) {
                saveGoogleUser(fbUser, credential);
            }

            @Override
            public void onFailure(String message) {
                Log.e("GoogleAuth", "Error: " + message);
                showToast("Erreur d'authentification Google");
            }
        });
    }

    private void saveGoogleUser(FirebaseUser fbUser, SignInCredential credential) {
        realm.executeTransactionAsync(r -> {
            User user = r.where(User.class)
                    .equalTo("email", fbUser.getEmail())
                    .findFirst();

            if (user == null) {
                Number maxId = r.where(User.class).max("idUser");
                int newId = (maxId == null) ? 1 : maxId.intValue() + 1;

                user = r.createObject(User.class, newId);
                user.setEmail(fbUser.getEmail());
                user.setFirstName(credential.getGivenName() != null ?
                        credential.getGivenName() : "");
                user.setLastName(credential.getFamilyName() != null ?
                        credential.getFamilyName() : "");
                user.setCurrency("MAD");
                user.setMonthlyBudget(0);
                user.setFirstLogin(1);
            }

            session.setUserId(user.getIdUser());
            session.setUserEmail(fbUser.getEmail());

        }, () -> {
            User refreshedUser = realm.where(User.class)
                    .equalTo("email", fbUser.getEmail())
                    .findFirst();

            if (refreshedUser != null) {
                runOnUiThread(() -> checkFirstLogin(refreshedUser));
            } else {
                runOnUiThread(() -> {
                    showToast("Utilisateur non trouvé après inscription Google");
                    redirectToMain();
                });
            }
        }, error -> {
            runOnUiThread(() -> showToast("Erreur Realm: " + error.getMessage()));
        });
    }
private void checkFirstLogin(User user) {
    if (user.getFirstLogin() == 1) {
        realm.executeTransactionAsync(r -> {
            user.setFirstLogin(0); // Mark as not first login
            r.insertOrUpdate(user);
        });

        startActivity(new Intent(this, WelcomeActivity.class));
    } else {
        redirectToMain();
    }
    finish();
}
    private void redirectToMain() {
        String email = session.getUserEmail();
        int    id    = session.getUserId();

        if (email != null && id > 0) {
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.putExtra("user_email", email);
            intent.putExtra("user_id",    id);
            startActivity(intent);
            finish();
        } else {
            session.clearSession();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}