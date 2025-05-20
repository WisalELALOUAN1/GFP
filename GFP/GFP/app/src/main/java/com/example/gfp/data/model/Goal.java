package com.example.gfp.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Goal extends RealmObject {
    @PrimaryKey
    private int idObj;

    private double budgetTotal;
    private double sommeEpargne;

    @Required
    private String description;

    @Required
    private String dateFin;

    // --- Getters/Setters ---
    public int getIdObj() {
        return idObj;
    }

    public void setIdObj(int idObj) {
        this.idObj = idObj;
    }

    public double getBudgetTotal() {
        return budgetTotal;
    }

    public void setBudgetTotal(double budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    public double getSommeEpargne() {
        return sommeEpargne;
    }

    public void setSommeEpargne(double sommeEpargne) {
        this.sommeEpargne = sommeEpargne;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isExpired() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            Date endDate = sdf.parse(dateFin);
            return endDate.before(new Date()) && getProgression() < 100;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isCompleted() {
        return getProgression() >= 100; // 100% ou plus = objectif atteint
    }




    public double getProgression() {
        return (sommeEpargne / budgetTotal) * 100;
    }
}