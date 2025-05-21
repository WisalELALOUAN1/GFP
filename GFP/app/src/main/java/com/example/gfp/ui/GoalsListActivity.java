package com.example.gfp.ui;

import static com.example.gfp.ui.NavbarManager.setupNavbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.model.User;
import com.example.gfp.data.session.SessionManager;
import com.example.gfp.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.realm.Realm;

@AndroidEntryPoint
public class GoalsListActivity extends AppCompatActivity {

    private RecyclerView goalsRecyclerView;
    private GoalsAdapter adapter;
    private UserViewModel userVM;
    private String userEmail;
    @Inject
    SessionManager session;

    public static final int REQUEST_EDIT_GOAL = 1001;

    @Override
    protected void onResume() {
        super.onResume();
        loadUserGoals();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_list);
        String userEmail = session.getUserEmail();
        userVM = new ViewModelProvider(this).get(UserViewModel.class);
        setupNavbar();
        BottomNav.setup(this);
        setupRecyclerView();
        loadUserGoals();

        FloatingActionButton addGoalButton = findViewById(R.id.addGoalButton);
        addGoalButton.setOnClickListener(view -> {
            Intent intent = new Intent(GoalsListActivity.this, DefineGoalActivity.class);
            intent.putExtra("user_email", userEmail);
            startActivity(intent);


        });
    }

    private void setupNavbar() {
        NavbarManager.setupNavbar(this, new NavbarManager.NavbarActionListener() {
            @Override
            public void onRefreshClicked() {
                Toast.makeText(GoalsListActivity.this,
                        "Refresh...",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLogoutClicked() {
                session.clearSession();

                Intent intent = new Intent(GoalsListActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();
            }
        });
    }
    public String getUserEmail() {
        return userEmail;
    }


    private void setupRecyclerView() {
        goalsRecyclerView = findViewById(R.id.goalsRecyclerView);
        goalsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GoalsAdapter(this);
        goalsRecyclerView.setAdapter(adapter);
    }


    private void loadUserGoals() {
        Log.d("GoalsListActivity", " loadUserGoals() called — userEmail=" + userEmail);

        String email = session.getUserEmail();
        User user = userVM.getUserByEmail(email);

        if (user != null) {
            Log.d("GoalsListActivity", " User trouvé, nombre d’objectifs=" +
                    (user.getGoals() != null ? user.getGoals().size() : "null"));
        } else {
            Log.d("GoalsListActivity", " Aucun user retourné pour cet email");
        }

        if (user != null && user.getGoals() != null) {
            adapter.setGoals(user.getGoals());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_GOAL && resultCode == RESULT_OK) {
            loadUserGoals();
        }
    }
}