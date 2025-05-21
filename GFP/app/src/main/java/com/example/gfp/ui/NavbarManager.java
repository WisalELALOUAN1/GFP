package com.example.gfp.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gfp.R;
import com.example.gfp.ui.DashboardActivity;

public class NavbarManager {

    public interface NavbarActionListener {
        void onRefreshClicked();
        void onLogoutClicked();
    }

    public static void setupNavbar(Activity activity, NavbarActionListener listener) {
        TextView tvAppName   = activity.findViewById(R.id.tv_app_name);
        ImageView btnSync    = activity.findViewById(R.id.btn_sync);
        ImageView btnLogout  = activity.findViewById(R.id.btn_logout);

        if (tvAppName != null) {
            tvAppName.setOnClickListener(v -> {
                if (!(activity instanceof DashboardActivity)) {
                    Intent intent = new Intent(activity, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    String email = activity.getIntent().getStringExtra("user_email");
                    if (email != null) intent.putExtra("user_email", email);
                    activity.startActivity(intent);
                }
            });
        }

        if (btnSync != null) {
            btnSync.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRefreshClicked();
                } else {
                    activity.recreate();
                }
            });
        }

        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onLogoutClicked();
                }
            });
        }
    }

    public static void hideButton(Activity activity, int buttonId, boolean hide) {
        View btn = activity.findViewById(buttonId);
        if (btn != null) btn.setVisibility(hide ? View.GONE : View.VISIBLE);
    }
}
