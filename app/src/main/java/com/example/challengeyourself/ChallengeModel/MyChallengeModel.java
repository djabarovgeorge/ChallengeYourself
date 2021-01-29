package com.example.challengeyourself.ChallengeModel;

import android.content.Context;
import com.example.challengeyourself.DataBase.MyChallengesTable;
import java.util.ArrayList;
import java.util.List;

public class MyChallengeModel {
    public final static MyChallengeModel instance = new MyChallengeModel();
    private MyChallengesTable myChallengesTable;

    private List<ChallengeTrack> myChallengesList = new ArrayList<>();


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

    public ChallengeTrack getChallengeObjectTrackById(int id) {
        ChallengeTrack item = getChallengeTrackById(id);
        return null;
    }

    public void addToMyChallenge(ChallengeTrack challenge){
        myChallengesTable.addChallenge(challenge);
    }

    public void setMyChallenge(ChallengeTrack challengeTrack) {
        for (ChallengeTrack challenge : myChallengesList) {
            if (challenge.getChallenge().getId() == challengeTrack.getChallenge().getId())
                challenge.setDayTrack(challengeTrack.getDayTrack());

        }

        myChallengesTable.setMyChallenge(challengeTrack);
    }

    public boolean isChallengeRegistered(int challengeId) {
        return myChallengesTable.isChallengeRegistered(challengeId);
    }
    public boolean clearTable(){
        return myChallengesTable.clearTable();
    }

    public ChallengeTrack getChallengeTrackObjectById(int id) {
        ChallengeTrack item = getChallengeTrackById(id);
        return null;
    }
}