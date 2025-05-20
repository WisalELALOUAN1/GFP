package com.example.gfp.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.gfp.data.model.User;
import com.example.gfp.data.repository.AuthRepository;
import com.example.gfp.data.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class UserViewModel extends ViewModel {

    private final AuthRepository authRepo = new AuthRepository();
    private final UserRepository userRepo = new UserRepository();

    public void login(String email, String pwd, AuthRepository.AuthCallback cb) {
        authRepo.login(email, pwd, cb);
    }
    public void register(String email, String pwd, AuthRepository.AuthCallback cb) {
        authRepo.register(email, pwd, cb);
    }
    public void signInWithGoogle(String idToken, AuthRepository.AuthCallback cb) {
        authRepo.signInWithGoogle(idToken, cb);
    }
    public boolean saveUserProfile(User user) {
        return userRepo.saveUserProfile(user);
    }
    public User getUserByEmail(String email) {
        return userRepo.getUserByFirebaseUid(email);
    }
    public void forgotPassword(String email, AuthRepository.VoidCallback cb) {
        authRepo.resetPassword(email, cb);
    }

    /** Si user inexistant en base, le créer **/
    public void saveIfMissing(FirebaseUser fUser) {
        if (fUser == null) return;
        if (userRepo.getUserByFirebaseUid(fUser.getEmail()) == null) {
            User u = new User();
            u.setEmail(fUser.getEmail());
            u.setLastName(fUser.getDisplayName() != null
                    ? fUser.getDisplayName() : "");
            u.setFirstName("");
            u.setPassword("");
            u.setCurrency("MAD");
            u.setMonthlyBudget(0);
            userRepo.saveUserProfile(u);
        }
    }
}
