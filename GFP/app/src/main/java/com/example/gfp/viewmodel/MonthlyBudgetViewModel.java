// com/example/gfp/viewmodel/MonthlyBudgetViewModel.java
package com.example.gfp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gfp.data.repository.UserRepository;
import com.example.gfp.data.session.SessionManager;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MonthlyBudgetViewModel extends ViewModel {

    private final UserRepository userRepo;
    private final SessionManager session;
    private final MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();

    @Inject
    public MonthlyBudgetViewModel(UserRepository userRepo,
                                  SessionManager session) {
        this.userRepo = userRepo;
        this.session  = session;
    }

    /** Pour que l’UI sache si la sauvegarde a réussi */
    public LiveData<Boolean> getSaveSuccess() {
        return saveSuccess;
    }

    /** Appelée par l’Activity pour enregistrer le budget */
    public void saveMonthlyBudget(double budget) {
        int userId = session.getUserId();
        boolean result = userRepo.updateMonthlyBudget(userId, budget);
        saveSuccess.setValue(result);
    }
}
