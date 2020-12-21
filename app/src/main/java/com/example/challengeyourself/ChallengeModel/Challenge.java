package com.example.challengeyourself.ChallengeModel;

public class Challenge {
    String id;
    String name;
    String numberOfDays;
    String freeText;

    public Challenge(String id, String name, String numberOfDays, String freeText) {
        this.id = id;
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.freeText = freeText;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getId() {
        return id;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public String getFreeText() {
        return freeText;
    }

    public String getName(){
        return name;
    }
}
