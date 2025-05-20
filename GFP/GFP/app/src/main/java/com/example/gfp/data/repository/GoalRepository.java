package com.example.gfp.data.repository;

import android.util.Log;

import com.example.gfp.data.model.Goal;
import com.example.gfp.data.model.User;
import com.example.gfp.data.session.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

@Singleton
public class GoalRepository {
    private static final String TAG = "GoalRepository";
    private final Realm realm;
    private final SessionManager sessionManager;
    private final SimpleDateFormat dateFormat;

    @Inject
    public GoalRepository(SessionManager sessionManager) {
        Log.d(TAG, "Initializing GoalRepository");
        this.realm = Realm.getDefaultInstance();
        this.sessionManager = sessionManager;
        this.dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Log.d(TAG, "Realm instance created: " + realm);
    }

    private List<Goal> filterGoals(RealmList<Goal> goals, java.util.function.Predicate<Goal> predicate) {
        Log.d(TAG, "Filtering goals list");
        if (goals == null) {
            Log.w(TAG, "Goals list is null, returning empty list");
            return new ArrayList<>();
        }

        Log.d(TAG, "Original goals count: " + goals.size());
        List<Goal> goalList = realm.copyFromRealm(goals);
        Log.d(TAG, "Copied goals count: " + goalList.size());

        List<Goal> filtered = goalList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        Log.d(TAG, "Filtered goals count: " + filtered.size());

        return filtered;
    }
    public List<Goal> getAllGoals(int userId) {
        Log.d(TAG, "Getting ALL goals for user: " + userId);

        User user = realm.where(User.class)
                .equalTo("idUser", userId)
                .findFirst();

        if (user != null && user.getGoals() != null) {
            Log.d(TAG, "User found, returning all " + user.getGoals().size() + " goals");
            return realm.copyFromRealm(user.getGoals()); // Retourne tous les goals sans filtre
        }

        Log.w(TAG, "User not found or has no goals");
        return new ArrayList<>();
    }
    public List<Goal> getCurrentGoals(int userId) {
        Log.d(TAG, "Getting current goals for user: " + userId);
        Date currentDate = new Date();
        Log.d(TAG, "Current date: " + currentDate);

        User user = realm.where(User.class)
                .equalTo("idUser", userId)
                .findFirst();

        if (user != null) {
            Log.d(TAG, "User found, goals count: " + (user.getGoals() != null ? user.getGoals().size() : 0));
            return filterGoals(user.getGoals(), goal -> {
                try {
                    Log.d(TAG, "Processing goal: " + goal.getIdObj());
                    Date goalDate = dateFormat.parse(goal.getDateFin());
                    boolean isCurrent = goalDate != null &&
                            goalDate.after(currentDate) &&
                            goal.getSommeEpargne() < goal.getBudgetTotal();
                    Log.d(TAG, "Goal " + goal.getIdObj() + " is current: " + isCurrent);
                    return isCurrent;
                } catch (ParseException e) {
                    Log.e(TAG, "Date parsing error for goal " + goal.getIdObj(), e);
                    return false;
                }
            });
        }
        Log.w(TAG, "User not found with id: " + userId);
        return new ArrayList<>();
    }

    public List<Goal> getCompletedGoals(int userId) {
        Log.d(TAG, "Getting completed goals for user: " + userId);
        User user = realm.where(User.class)
                .equalTo("idUser", userId)
                .findFirst();

        if (user != null) {
            Log.d(TAG, "User found, goals count: " + (user.getGoals() != null ? user.getGoals().size() : 0));
            return filterGoals(user.getGoals(), goal -> {
                boolean isCompleted = goal.getSommeEpargne() >= goal.getBudgetTotal();
                Log.d(TAG, "Goal " + goal.getIdObj() + " is completed: " + isCompleted);
                return isCompleted;
            });
        }
        Log.w(TAG, "User not found with id: " + userId);
        return new ArrayList<>();
    }

    public List<Goal> getExpiredGoals(int userId) {
        Log.d(TAG, "Getting expired goals for user: " + userId);
        Date currentDate = new Date();
        Log.d(TAG, "Current date: " + currentDate);

        User user = realm.where(User.class)
                .equalTo("idUser", userId)
                .findFirst();

        if (user != null) {
            Log.d(TAG, "User found, goals count: " + (user.getGoals() != null ? user.getGoals().size() : 0));
            return filterGoals(user.getGoals(), goal -> {
                try {
                    Log.d(TAG, "Processing goal: " + goal.getIdObj());
                    Date goalDate = dateFormat.parse(goal.getDateFin());
                    boolean isExpired = goalDate != null &&
                            goalDate.before(currentDate) &&
                            goal.getSommeEpargne() < goal.getBudgetTotal();
                    Log.d(TAG, "Goal " + goal.getIdObj() + " is expired: " + isExpired);
                    return isExpired;
                } catch (ParseException e) {
                    Log.e(TAG, "Date parsing error for goal " + goal.getIdObj(), e);
                    return false;
                }
            });
        }
        Log.w(TAG, "User not found with id: " + userId);
        return new ArrayList<>();
    }

    public void createGoal(Goal goal, int userId) {
        Log.d(TAG, "Creating new goal for user: " + userId);
        realm.executeTransaction(r -> {
            Number maxId = r.where(Goal.class).max("idObj");
            int newId = (maxId != null) ? maxId.intValue() + 1 : 1;
            goal.setIdObj(newId);
            Log.d(TAG, "New goal ID: " + newId);

            User user = r.where(User.class).equalTo("idUser", userId).findFirst();
            if (user != null) {
                Log.d(TAG, "Adding goal to user's goals list");
                user.addGoal(goal);
                r.insertOrUpdate(user);
            } else {
                Log.e(TAG, "User not found when creating goal");
            }

            r.insertOrUpdate(goal);
            Log.d(TAG, "Goal created successfully");
        });
    }

    public void close() {
        if (realm != null && !realm.isClosed()) {
            Log.d(TAG, "Closing Realm instance");
            realm.close();
        }
    }
}