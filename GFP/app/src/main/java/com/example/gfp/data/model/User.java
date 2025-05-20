package com.example.gfp.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {

    @PrimaryKey
    private int id;

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

    // --- Getters et Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    // --- Méthodes supplémentaires ---
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

