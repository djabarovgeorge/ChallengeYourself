package com.example.challengeyourself.ChallengeModel;

import java.util.ArrayList;
import java.util.List;

public class ChallengeModel {

    public final static ChallengeModel instance = new ChallengeModel();
    private List<Challenge> challengesList = new ArrayList<Challenge>();

    private ChallengeModel(){
        for (int i=0 ; i<10 ; i++)
        {
            challengesList.add(new Challenge(Integer.toString(i),"gosha" + Integer.toString(i),"6","Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
        }
    };


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
