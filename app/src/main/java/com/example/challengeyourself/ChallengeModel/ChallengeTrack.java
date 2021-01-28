package com.example.challengeyourself.ChallengeModel;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class ChallengeTrack {
    private Challenge challenge;
    private Hashtable<Integer, Boolean> dayTrack; // the index represents the day
    private ArrayList<DayTrack> dayTrackList;


//    public ChallengeTrack(Challenge challenge) {
//        this.challenge = challenge;
//        List<Boolean> track;
//        dayTrack = new Hashtable<Integer,Boolean>();
//        for (int i = 0; i < challenge.getNumberOfDays(); i++) {
//            dayTrack.put(i+1, false);
//        }
//    }

    public ChallengeTrack(Challenge challenge) {
        this.challenge = challenge;
        dayTrackList = new ArrayList<DayTrack>();
        for (int i = 0; i < challenge.getNumberOfDays(); i++) {
            DayTrack item = new DayTrack(i + 1, false);
            dayTrackList.add(item);
        }
        dayTrack = new Hashtable<Integer,Boolean>();
        for (int i = 0; i < challenge.getNumberOfDays(); i++) {
            dayTrack.put(i+1, false);
        }
    }

    public ChallengeTrack(Challenge challenge, Hashtable<Integer, Boolean> dayTrack) {
        this.challenge = challenge;
        this.dayTrack = dayTrack;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void setDayTrack(Hashtable<Integer, Boolean> dayTrack) {
        this.dayTrack = dayTrack;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Hashtable<Integer, Boolean> getDayTrack() {
        return dayTrack;
    }

    public ArrayList<DayTrack> getDayTrackList() {
        return dayTrackList;
    }

    public void setDayTrackList(ArrayList<DayTrack> dayTrackList) { this.dayTrackList = dayTrackList; }


}
