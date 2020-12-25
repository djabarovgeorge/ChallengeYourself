package com.example.challengeyourself.ChallengeModel;

import android.content.Context;

import com.example.challengeyourself.DataBase.MyChallengesTable;

import java.util.ArrayList;
import java.util.List;

public class MyChallengeModel {
    public final static MyChallengeModel instance = new MyChallengeModel();
    private List<ChallengeTrack> challengesList = new ArrayList<ChallengeTrack>();

    private MyChallengesTable myChallengesTable;


    // TODO init from main activity
    public void initDatabase(Context context){
        myChallengesTable = new MyChallengesTable(context);
    }

    private MyChallengeModel(){ };


    public List<ChallengeTrack> getChallengesList() {return myChallengesTable.getAllChallenges();}

//    public MyChallenges getChallenge(int id) {
////        List<MyChallenges> chList = getChallengesList();
//        for (MyChallenges challenge: chList) {
////            if(challenge.id == id)
//                return challenge;
//        }
//        return null;
//    }

    public void addChallenge(ChallengeTrack challenge){
//        myChallengesTable.addChallenge(challenge);
    }
}