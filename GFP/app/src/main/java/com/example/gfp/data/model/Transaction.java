package com.example.gfp.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Transaction extends RealmObject {

    @PrimaryKey
    private int idTransaction;

    @Required
    private String time;

    @Required
    private String description;
    private double amount;

    @Required
    private String type;

    private int idUserCategory;

    public int getIdTransaction() { return idTransaction; }
    public void setIdTransaction(int idTransaction) { this.idTransaction = idTransaction; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getIdUserCategory() { return idUserCategory; }
    public void setIdUserCategory(int idUserCategory) { this.idUserCategory = idUserCategory; }
}
