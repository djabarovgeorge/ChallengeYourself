package com.example.challengeyourself.ChallengeModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.challengeyourself.DataBase.MyChallengesTable;

import java.util.ArrayList;
import java.util.List;

public class MyChallengeModel {
    public final static MyChallengeModel instance = new MyChallengeModel();
    private List<ChallengeTrack> myChallengesList = new ArrayList<>();

    private MyChallengesTable myChallengesTable;


    // TODO init from main activity
    public void initDatabase(Context context){
        myChallengesTable = new MyChallengesTable(context);
    }

    private MyChallengeModel(){ };


    public List<ChallengeTrack> getChallengesList() {return myChallengesTable.getAllChallenges();}

    public ChallengeTrack getChallengeTrackById(int id) {
        List<ChallengeTrack> chList = getChallengesList();
        for (ChallengeTrack challenge: chList) {
            if(challenge.getChallenge().getId() == id)
                return challenge;
        }
        return null;
    }

    public void addToMyChallenge(ChallengeTrack challenge){
        myChallengesTable.addChallenge(challenge);
    }

    public boolean isChallengeRegistered(int challengeId) {
        return myChallengesTable.isChallengeRegistered(challengeId);
    }

}