package com.example.gfp.viewmodel;


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
