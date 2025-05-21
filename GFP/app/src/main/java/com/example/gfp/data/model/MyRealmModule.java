package com.example.gfp.data.model;

import io.realm.annotations.RealmModule;

@RealmModule(classes = { User.class,
        Goal.class,
        Transaction.class,
        Category.class,
        UserCategory.class })
public class MyRealmModule {
}
