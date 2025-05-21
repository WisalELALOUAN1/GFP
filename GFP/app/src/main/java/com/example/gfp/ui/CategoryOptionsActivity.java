// CategoryOptionsActivity.java
package com.example.gfp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.gfp.R;
import com.example.gfp.data.model.Category;
import com.example.gfp.data.model.Option;
import com.example.gfp.data.session.SessionManager;
import com.example.gfp.viewmodel.CategoryViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryOptionsActivity extends AppCompatActivity {
    @Inject
    SessionManager session;
    private static final String TAG = "CategoryOptionsActivity";

    private LinearLayout optionsContainer;
    private ProgressBar progressBar;
    private TextView categoryTitle, categoryIcon;
    private MaterialButton continueButton;
    private View noneView;
    private RadioButton noneRadio;
    private boolean noneSelected = false;

    private int userId;
    private String userEmail;
    private CategoryViewModel viewModel;
    private final Set<Integer> selectedOptionIds = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_options);

        userId    = session.getUserId();
        userEmail = session.getUserEmail();
        optionsContainer = findViewById(R.id.options_container);
        progressBar      = findViewById(R.id.progress_bar);
        categoryTitle    = findViewById(R.id.category_title);
        categoryIcon     = findViewById(R.id.category_icon);
        continueButton   = findViewById(R.id.continue_button);


        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        viewModel.getCurrentCategoryLiveData().observe(this, this::displayCategory);
        viewModel.getProgressLiveData()
                .observe(this, progress -> progressBar.setProgress(progress));

        setupListeners();


    }

    private void setupListeners() {
        continueButton.setOnClickListener(v -> {
            Log.d(TAG, "Continue clicked, selections="
                    + selectedOptionIds + ", none=" + noneSelected);

            Category current = viewModel.getCurrentCategoryLiveData().getValue();
            if (current == null) return;

            if (noneSelected) {
                Log.d(TAG, "User chose NONE → skip saving for cat="
                        + current.getIdCategory());
            } else if (!selectedOptionIds.isEmpty()) {
                for (Integer optId : selectedOptionIds) {
                    viewModel.saveUserSelection(current.getIdCategory(), optId);
                }
            } else {
                Toast.makeText(this,
                        "Please select at least one option or \"None of these apply to me\"",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!viewModel.nextCategory()) {
                Toast.makeText(this, "All categories processed",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CategoryOptionsActivity.this, MonthlyBudgetActivity.class);
                intent.putExtra("user_email", userEmail);
                startActivity(intent);
                finish();
            }
        });
    }

    private void displayCategory(Category category) {
        selectedOptionIds.clear();
        noneSelected = false;

        categoryTitle.setText(category.getCategoryName());
        setIconForCategory(category.getCategoryName());
        optionsContainer.removeAllViews();

        if (category.getOptions() != null) {
            for (Option opt : category.getOptions()) {
                optionsContainer.addView(createOptionView(opt));
            }
        }

        optionsContainer.addView(createNoneOptionView());
    }

    private void setIconForCategory(String name) {
        String emoji = "🏠";
        switch (name.toLowerCase()) {
            case "food": emoji = "🍔"; break;
            case "restaurants": emoji = "🍽️"; break;
            case "shopping": emoji = "🛍️"; break;
            case "fuel": emoji = "⛽"; break;
            case "leisure": emoji = "🎭"; break;
            case "phone": emoji = "📱"; break;
            case "internet": emoji = "🌐"; break;
            case "bills": emoji = "💰"; break;
            case "credit": emoji = "💳"; break;
            case "home": emoji = "🏠"; break;
            case "travel": emoji = "✈️"; break;
            case "health": emoji = "🏥"; break;
            case "transportation": emoji = "🚌"; break;
            case "insurance": emoji = "🔒"; break;
            case "education": emoji = "🎓"; break;
        }
        categoryIcon.setText(emoji);
    }

    private View createOptionView(Option option) {
        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.option_height)
        );
        lp.setMargins(0, 0, 0,
                getResources().getDimensionPixelSize(R.dimen.option_margin_bottom));
        card.setLayoutParams(lp);
        card.setRadius(getResources().getDimension(R.dimen.card_corner_radius));
        card.setCardElevation(getResources().getDimension(R.dimen.card_elevation));
        card.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.option_background)
        );
        card.setClickable(true);
        card.setFocusable(true);

        RadioButton rb = new RadioButton(this);
        rb.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        rb.setText(option.getOptionName());
        rb.setTextSize(18);
        rb.setButtonDrawable(R.drawable.radio_selector);
        card.addView(rb);

        View.OnClickListener toggleNormal = v -> {
            if (noneSelected) {
                noneSelected = false;
                noneRadio.setChecked(false);
                noneView.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.option_background)
                );
            }
            int idOpt = option.getIdOption();
            if (selectedOptionIds.contains(idOpt)) {
                selectedOptionIds.remove(idOpt);
                rb.setChecked(false);
                card.setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.option_background)
                );
            } else {
                selectedOptionIds.add(idOpt);
                rb.setChecked(true);
                card.setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.selected_option_background)
                );
            }
            Log.d(TAG, "Normal selections=" + selectedOptionIds);
        };

        card.setOnClickListener(toggleNormal);
        rb.setOnClickListener(toggleNormal);
        return card;
    }

    private View createNoneOptionView() {
        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.option_height)
        );
        lp.setMargins(0, 0, 0,
                getResources().getDimensionPixelSize(R.dimen.option_margin_bottom));
        card.setLayoutParams(lp);
        card.setRadius(getResources().getDimension(R.dimen.card_corner_radius));
        card.setCardElevation(getResources().getDimension(R.dimen.card_elevation));
        card.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.option_background)
        );
        card.setClickable(true);
        card.setFocusable(true);

        RadioButton rb = new RadioButton(this);
        rb.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        rb.setText("None of these apply to me");
        rb.setTextSize(18);
        rb.setButtonDrawable(R.drawable.radio_selector);
        rb.setCompoundDrawablePadding(
                getResources().getDimensionPixelSize(R.dimen.radio_padding)
        );
        card.addView(rb);

        View.OnClickListener toggleNone = v -> {
            if (noneSelected) {
                noneSelected = false;
                rb.setChecked(false);
                card.setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.option_background)
                );
            } else {
                noneSelected = true;
                rb.setChecked(true);
                card.setCardBackgroundColor(
                        ContextCompat.getColor(this, R.color.selected_option_background)
                );
                // Décocher toutes les normales
                for (int i = 0; i < optionsContainer.getChildCount(); i++) {
                    View child = optionsContainer.getChildAt(i);
                    if (child instanceof MaterialCardView && child != card) {
                        MaterialCardView c2 = (MaterialCardView) child;
                        RadioButton r2 = (RadioButton) c2.getChildAt(0);
                        r2.setChecked(false);
                        c2.setCardBackgroundColor(
                                ContextCompat.getColor(this, R.color.option_background)
                        );
                    }
                }
                selectedOptionIds.clear();
            }
            Log.d(TAG, "None selected=" + noneSelected);
        };

        card.setOnClickListener(toggleNone);
        rb.setOnClickListener(toggleNone);

        noneView  = card;
        noneRadio = rb;
        return card;
    }
}
