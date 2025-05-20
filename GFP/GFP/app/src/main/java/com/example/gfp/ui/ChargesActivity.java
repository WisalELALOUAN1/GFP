// com/example/gfp/ui/ChargesActivity.java
package com.example.gfp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.session.SessionManager;
import com.example.gfp.viewmodel.CategoryDisplay;
import com.example.gfp.viewmodel.ChargesViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChargesActivity extends AppCompatActivity {
    @Inject SessionManager session;

    private ChargesViewModel viewModel;
    private CategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charges);

        RecyclerView rv = findViewById(R.id.charges_recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new ViewModelProvider(this).get(ChargesViewModel.class);

        viewModel.getSaveSuccess().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                navigateToDashboard();
            }
        });
        viewModel.getCategoriesLiveData().observe(this, cats -> {
            adapter = new CategoriesAdapter(cats);
            rv.setAdapter(adapter);
        });

        MaterialButton letsGo = findViewById(R.id.lets_go_button);
        letsGo.setOnClickListener(v -> {
            if (adapter != null) {
                List<CategoryDisplay> allCats = adapter.getData();
                viewModel.saveCategoryDisplays(allCats);
                Toast.makeText(this,
                        "Your changes have been saved!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void navigateToDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
