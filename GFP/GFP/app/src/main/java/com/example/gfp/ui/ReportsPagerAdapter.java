package com.example.gfp.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ReportsPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_TABS = 2;

    public ReportsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TransactionsReportFragment();
            case 1:
                return new GoalsReportFragment();
            default: throw new IllegalArgumentException("Invalid tab position");
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}