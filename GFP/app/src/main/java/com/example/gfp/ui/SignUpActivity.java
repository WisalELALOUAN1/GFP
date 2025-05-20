package com.example.gfp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gfp.R;
import com.example.gfp.data.model.User;

import com.example.gfp.data.repository.AuthRepository;
import com.example.gfp.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private TextInputEditText etNom, etPrenom, etEmail, etPassword, etConfirm;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        etNom = findViewById(R.id.etNomUser);
        etPrenom = findViewById(R.id.etPrenomUser);
        etEmail = findViewById(R.id.etEmailSignUp);
        etPassword = findViewById(R.id.etPasswordSignUp);
        etConfirm = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String prenom = etPrenom.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString();
            String confirm = etConfirm.getText().toString();

            if (!pass.equals(confirm)) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                return;
            }

            userViewModel.register(email, pass, new AuthRepository.AuthCallback() {
                @Override
                public void onSuccess(FirebaseUser firebaseUser) {
                    User user = new User();
                    user.setEmail(firebaseUser.getEmail());
                    user.setLastName(nom);
                    user.setFirstName(prenom);
                    user.setPassword(pass);
                    user.setCurrency("MAD");
                    user.setMonthlyBudget(0);
                    Log.d("SIGN_UP_FLOW", "✅ Avant sauvegarde dans Realm");
                    userViewModel.saveUserProfile(user);

                    Toast.makeText(SignUpActivity.this, "Compte créé !", Toast.LENGTH_SHORT).show();
                    finish(); // retour au login
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(SignUpActivity.this, "Erreur : " + message, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
