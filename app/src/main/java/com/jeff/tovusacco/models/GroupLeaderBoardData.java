package com.jeff.tovusacco.models;

public class GroupLeaderBoardData {

    private String groupName;
    private String amount;

    public GroupLeaderBoardData() {
        this.groupName = "";
        this.amount = "";
    }

    public GroupLeaderBoardData(String groupName, String amount) {
        this.groupName = groupName;
        this.amount = amount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
