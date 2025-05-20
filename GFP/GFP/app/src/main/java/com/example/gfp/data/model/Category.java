package com.example.gfp.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import io.realm.RealmList;

public class Category extends RealmObject {
    @PrimaryKey
    private int idCategory;
    private String categoryName;
    private RealmList<Option> options;  // Relation vers la classe Option

    // Getters et Setters
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public RealmList<Option> getOptions() {
        return options;
    }

    public void setOptions(RealmList<Option> options) {
        this.options = options;
    }
}

