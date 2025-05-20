package com.example.gfp.viewmodel;

/**
 * Représente une option de catégorie, avec son nom, son budget et si elle est fixe.
 */
public class OptionDisplay {
    public final int optionId;
    public final String optionName;
    public double budget;
    public boolean isFixed;

    public OptionDisplay(int optionId, String optionName, double budget, boolean isFixed) {
        this.optionId   = optionId;
        this.optionName = optionName;
        this.budget     = budget;
        this.isFixed    = isFixed;
    }
}
