package com.example.gfp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.model.Goal;

import java.util.List;

public class GoalsReportFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView emptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals_report, container, false);

        recyclerView = view.findViewById(R.id.goalsRecyclerView);
        emptyView = view.findViewById(R.id.emptyView);

        showEmptyView(true);

        loadGoals();

        return view;
    }

    private void loadGoals() {
        List<Goal> goals = ((ReportsActivity)requireActivity())
                .getGoalRepository()
                .getCurrentGoals(((ReportsActivity)requireActivity()).getUserId());

        if (goals.isEmpty()) {
            showEmptyView(true);
        } else {
            showEmptyView(false);
            GoalsAdapter adapter = new GoalsAdapter(getContext(), goals);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    private void showEmptyView(boolean show) {
        if (show) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}