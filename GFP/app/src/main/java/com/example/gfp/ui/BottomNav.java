package com.example.gfp.ui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gfp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav {
    public static void setup(AppCompatActivity activity) {
        BottomNavigationView nav = activity.findViewById(R.id.bottom_navigation);
        if (nav == null) return;

        String current = activity.getClass().getSimpleName();
        if (current.equals("AnalyseActivity"))      nav.setSelectedItemId(R.id.nav_analyse);
        else if (current.equals("ReportsActivity")) nav.setSelectedItemId(R.id.nav_reports);
        else if (current.equals("TransactionsActivity")) nav.setSelectedItemId(R.id.nav_transactions);
        else if (current.equals("GoalsListActivity"))    nav.setSelectedItemId(R.id.nav_goals);

        nav.setOnItemSelectedListener(item -> {
            Intent intent = null;
            int id = item.getItemId();
            if (id == R.id.nav_analyse && !"AnalyseActivity".equals(current)) {
                intent = new Intent(activity, AnalyseActivity.class);
            } else if (id == R.id.nav_reports && !"ReportsActivity".equals(current)) {
                intent = new Intent(activity, ReportsActivity.class);
            } else if (id == R.id.nav_transactions && !"TransactionsActivity".equals(current)) {
                intent = new Intent(activity, TransactionsActivity.class);
            } else if (id == R.id.nav_goals && !"GoalsListActivity".equals(current)) {
                intent = new Intent(activity, GoalsListActivity.class);
            }
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                activity.startActivity(intent);
                activity.overridePendingTransition(0, 0);
                activity.finish();
            }
            return true;
        });
    }
}
