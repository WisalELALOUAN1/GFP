package com.example.gfp.data.session;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String PREFS           = "user_prefs";
    private static final String KEY_USER_ID     = "current_user_id";
    private static final String KEY_USER_EMAIL  = "current_user_email";

    private final SharedPreferences prefs;

    @Inject
    public SessionManager(Context context) {
        this.prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public int getUserId() {
        return prefs.getInt(KEY_USER_ID, -1);
    }
    public void setUserId(int id) {
        prefs.edit().putInt(KEY_USER_ID, id).apply();
    }

    public String getUserEmail() {
        return prefs.getString(KEY_USER_EMAIL, null);
    }
    public void setUserEmail(String email) {
        prefs.edit().putString(KEY_USER_EMAIL, email).apply();
    }

    public void clearSession() {
        prefs.edit().clear().apply();
    }
}
