package com.example.gfp.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Option extends RealmObject {
    @PrimaryKey
    private int idOption;
    private String optionName;

    public Option(){}
    public Option(int i, String s){
        this.idOption = i;
        this.optionName = s;
    }
    // Getters et Setters
    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}

