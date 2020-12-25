package com.example.challengeyourself.ChallengeModel;

public class Challenge {
    int id;
    String name;
    int numberOfDays;
    String freeText;

    public Challenge(String name, int numberOfDays, String freeText) {
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.freeText = freeText;
    }

    public Challenge(int id, String name, int numberOfDays, String freeText) {
        this.id = id;
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.freeText = freeText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getFreeText() {
        return freeText;
    }

    public String getName(){ return name; }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfDays=" + numberOfDays +
                ", freeText='" + freeText + '\'' +
                '}';
    }

}
