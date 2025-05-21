package com.example.gfp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gfp.R;
import com.example.gfp.data.model.User;
import com.example.gfp.data.session.SessionManager;

import javax.inject.Inject;

import io.realm.Realm;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WelcomeActivity extends AppCompatActivity {

    @Inject
    SessionManager session;

    private TextView tvWelcomeMessage;
    private Button   btnContinue;
    private Realm    realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        realm = Realm.getDefaultInstance();

        tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage);
        btnContinue      = findViewById(R.id.btnContinue);

        int userId = session.getUserId();
        if (userId < 0) {
            Toast.makeText(this,
                    "Aucun utilisateur connecté", Toast.LENGTH_LONG).show();
            Log.e("WelcomeActivity", "SessionManager returned invalid userId");
            return;
        }

        User currentUser = realm.where(User.class)
                .equalTo("idUser", userId)
                .findFirst();

        if (currentUser != null) {
            String msg = "Welcome " + currentUser.getFirstName() + " to DirhamWay!";
            tvWelcomeMessage.setText(msg);
        } else {
            Toast.makeText(this,
                    "Utilisateur introuvable en base", Toast.LENGTH_LONG).show();
            Log.e("WelcomeActivity", "No User with id=" + userId);
        }

        btnContinue.setOnClickListener(v -> {
            try {
                startActivity(new Intent(this, CategoryOptionsActivity.class));
                finish();
            } catch (Exception e) {
                Toast.makeText(this,
                        "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("WelcomeActivity",
                        "Erreur lors du lancement de CategoryOptionsActivity", e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
