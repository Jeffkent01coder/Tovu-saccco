package com.jeff.tovusacco.models;

public class PersonalLeaderBoardData {

    private String personalName;
    private String amount;

    public PersonalLeaderBoardData() {
        this.personalName = "";
        this.amount = "";
    }

    public PersonalLeaderBoardData(String personalName, String amount) {
        this.personalName = personalName;
        this.amount = amount;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
