package com.example.gfp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.User;
import com.example.gfp.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalViewHolder> {
    private Context context;
    private List<Goal> goals = new ArrayList<>();

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
        notifyDataSetChanged();
    }


    public GoalsAdapter(Context context, List<Goal> goals) {
        this.context = context;
        this.goals = goals;
    }

    public GoalsAdapter(Context context, LayoutInflater inflater, List<Goal> goals) {
        this.context = context;
        this.goals = goals;
    }
    public GoalsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goal, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        Goal goal = goals.get(position);
        holder.bind(goal);

        holder.itemView.setOnClickListener(view -> {

            showGoalOptionsDialog(goal);
        });
    }
    private void showGoalOptionsDialog(Goal goal) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose action")
                .setItems(new CharSequence[]{"Modify", "Delete"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            modifyGoal(goal);
                            break;
                        case 1:
                            deleteGoal(goal.getIdObj());
                            break;
                    }
                })
                .create()
                .show();
    }
    private void modifyGoal(Goal goal) {
        Intent intent = new Intent(context, ModifyGoalActivity.class);
        intent.putExtra("goal_id", goal.getIdObj());
        if (context instanceof GoalsListActivity) {
            ((GoalsListActivity) context)
                    .startActivityForResult(intent, GoalsListActivity.REQUEST_EDIT_GOAL);
        } else {
            context.startActivity(intent);
        }
    }


    public void deleteGoal(int goalId) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            Goal goalToDelete = r.where(Goal.class).equalTo("idObj", goalId).findFirst();
            if (goalToDelete != null) {
                goalToDelete.deleteFromRealm();
                Toast.makeText(context, "Objectif supprimé avec succès!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "Objectif introuvable", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return goals.size();
    }

    static class GoalViewHolder extends RecyclerView.ViewHolder {
        TextView goalTitle, targetAmount, savedAmount, deadline, progressText;
        ProgressBar progressBar;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goalTitle = itemView.findViewById(R.id.goalTitle);
            targetAmount = itemView.findViewById(R.id.targetAmount);
            savedAmount = itemView.findViewById(R.id.savedAmount);
            deadline = itemView.findViewById(R.id.deadline);
            progressText = itemView.findViewById(R.id.progressText);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        public void bind(Goal goal) {
            goalTitle.setText(goal.getDescription());
            targetAmount.setText("Objectif: " + goal.getBudgetTotal() + " DH");
            savedAmount.setText("Épargné: " + goal.getSommeEpargne() + " DH");
            deadline.setText("Date limite: " + goal.getDateFin());

            double progress = (goal.getSommeEpargne() / goal.getBudgetTotal()) * 100;
            String formattedProgress = String.format("%.2f", progress);
            progressText.setText(formattedProgress + "%");
            progressBar.setProgress((int) progress);
        }
    }
}
