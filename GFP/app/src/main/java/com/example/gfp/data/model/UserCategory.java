package com.example.gfp.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserCategory extends RealmObject {
    @PrimaryKey
    private int idUserCategory;
    private int idUser;
    private int idCategory;
    private int idOption;
    private double recommendedBudget;
    private boolean isFixed;
    private double finalBudget;

    public int getIdUserCategory() {
        return idUserCategory;
    }

    public void setIdUserCategory(int idUserCategory) {
        this.idUserCategory = idUserCategory;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    public double getRecommendedBudget() {
        return recommendedBudget;
    }

    public void setRecommendedBudget(double recommendedBudget) {
        this.recommendedBudget = recommendedBudget;
    }

    public double getFinalBudget() {
        return finalBudget;
    }

    public void setFinalBudget(double finalBudget) {
        this.finalBudget = finalBudget;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }
}
