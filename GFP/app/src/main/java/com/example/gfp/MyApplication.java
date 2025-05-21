package com.example.gfp;

import android.app.Application;
import android.util.Log;

import dagger.hilt.android.HiltAndroidApp;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@HiltAndroidApp
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);


        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .addModule(new com.example.gfp.data.model.MyRealmModule())
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public static void resetRealmDatabase() {
        Realm realm = Realm.getDefaultInstance();
        realm.close();

        Realm.deleteRealm(Realm.getDefaultConfiguration());
        Log.d("REALM_RESET", "Base Realm complètement supprimée !");
    }
}
