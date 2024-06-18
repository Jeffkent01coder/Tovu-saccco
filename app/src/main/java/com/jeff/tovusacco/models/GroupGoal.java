package com.jeff.tovusacco.models;


public class GroupGoal {

    private String goalTitle;
    private String currentSavingsAmount;
    private String savingsGoalAmount;
    private String progressPercentage;

    public GroupGoal() {
        this.goalTitle = "";
        this.currentSavingsAmount = "";
        this.savingsGoalAmount = "";
        this.progressPercentage = "";
    }

    public GroupGoal(String goalTitle, String currentSavingsAmount, String savingsGoalAmount, String progressPercentage) {
        this.goalTitle = goalTitle;
        this.currentSavingsAmount = currentSavingsAmount;
        this.savingsGoalAmount = savingsGoalAmount;
        this.progressPercentage = progressPercentage;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }

    public String getCurrentSavingsAmount() {
        return currentSavingsAmount;
    }

    public void setCurrentSavingsAmount(String currentSavingsAmount) {
        this.currentSavingsAmount = currentSavingsAmount;
    }

    public String getSavingsGoalAmount() {
        return savingsGoalAmount;
    }

    public void setSavingsGoalAmount(String savingsGoalAmount) {
        this.savingsGoalAmount = savingsGoalAmount;
    }

    public String getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(String progressPercentage) {
        this.progressPercentage = progressPercentage;
    }
}

