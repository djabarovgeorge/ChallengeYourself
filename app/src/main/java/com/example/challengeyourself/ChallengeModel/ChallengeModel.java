package com.example.challengeyourself.ChallengeModel;

import java.util.ArrayList;
import java.util.List;

public class ChallengeModel {
    public final static ChallengeModel instance = new ChallengeModel();
    private ChallengeModel(){};

    List<Challenge> challengesList = new ArrayList<Challenge>();

    public List<Challenge> getChallengesList() {
        return challengesList;
    }

    public Challenge getChallenge(String id) {
        for (Challenge challenge: challengesList) {
            if(challenge.id.equals(id))
                return challenge;
        }
        return null;
    }

    public void addChallenge(Challenge challenge){
        challengesList.add(challenge);
    }
}
