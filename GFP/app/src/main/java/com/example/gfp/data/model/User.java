package com.example.gfp.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {

    @PrimaryKey
    private int idUser;

    @Required
    private String email;

    @Required
    private String lastName;

    @Required
    private String firstName;

    @Required
    private String password;

    private String currency;
    private double monthlyBudget;
    private int firstLogin;
    private RealmList<Goal> goals;


    public int getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(int firstLogin) {
        this.firstLogin = firstLogin;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int id) { this.idUser = id; }
    public RealmList<Goal> getGoals() {
        return goals;
    }

    public void setGoals(RealmList<Goal> goals) {
        this.goals = goals;
    }

    public void addGoal(Goal goal) {
        if (goals == null) {
            goals = new RealmList<>();
        }
        goals.add(goal);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public void modifierProfil(String nom, String prenom, String devise, double budget) {
        this.lastName = nom;
        this.firstName = prenom;
        this.currency = devise;
        this.monthlyBudget = budget;
    }

    public double getBudget() {
        return monthlyBudget;
    }

    public String getRapport() {
        return "Nom : " + lastName + "\nPrénom : " + firstName +
                "\nBudget : " + monthlyBudget + " " + currency;
    }

}

