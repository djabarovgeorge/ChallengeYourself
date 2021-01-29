package com.example.challengeyourself.ChallengeModel;

import android.content.Context;

import com.example.challengeyourself.DataBase.ChallengesTable;

import java.util.ArrayList;
import java.util.List;

public class ChallengeModel {

    public final static ChallengeModel instance = new ChallengeModel();
    private List<Challenge> challengesList = new ArrayList<Challenge>();

    private ChallengesTable challengesTable;

    public void initDatabase(Context context){
        challengesTable = new ChallengesTable(context);
    }

    private ChallengeModel(){ };


    public List<Challenge> getChallengesList() {return challengesTable.getAllChallenges();}

    public Challenge getChallenge(int id) {
        List<Challenge> chList = getChallengesList();
        for (Challenge challenge: chList) {
            if(challenge.getId() == id)
                return challenge;
        }
        return null;
    }

    public void addChallenge(Challenge challenge){
        challengesTable.addChallenge(challenge);
    }

    public boolean clearTable(){
        return challengesTable.clearTable();
    }

    public void deleteChallenge(int challengeId) {
        challengesTable.deleteChallenge(challengeId);
    }
}
