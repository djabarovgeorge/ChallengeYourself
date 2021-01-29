package com.example.challengeyourself.ChallengeModel;

import java.util.Arrays;

public class Challenge {
    private byte[] image;
    private int id;
    private String name;
    private int numberOfDays;
    private String freeText;

    public Challenge(String name, int numberOfDays, String freeText, byte[] image) {
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.freeText = freeText;
        this.image = image;
    }

    public Challenge(int id, String name, int numberOfDays, String freeText, byte[] image) {
        this.id = id;
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.freeText = freeText;
        this.image = image;
    }

    public Challenge(Challenge challenge) {
        this.id = challenge.getId();
        this.name = challenge.getName();
        this.numberOfDays = challenge.getNumberOfDays();
        this.freeText = challenge.getFreeText();
        this.image = challenge.getImage();
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

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
                "image=" + Arrays.toString(image) +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", numberOfDays=" + numberOfDays +
                ", freeText='" + freeText + '\'' +
                '}';
    }
}
