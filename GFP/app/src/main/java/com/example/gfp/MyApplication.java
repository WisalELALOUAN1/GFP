package com.example.gfp;

import android.app.Application;

import com.example.gfp.data.model.MyRealmModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1) // Version explicite
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .addModule(new com.example.gfp.data.model.MyRealmModule()) // Ajoutez le module
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
