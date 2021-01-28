package com.example.challengeyourself.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;
import com.example.challengeyourself.ChallengeModel.ChallengeTrack;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class MyChallengesTable extends SQLiteOpenHelper {


    public static final String MY_CHALLENGES_TABLE = "MY_CHALLENGES_TABLE";

    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_CHALLENGE_ID = "COLUMN_CHALLENGE_ID";
    public static final String COLUMN_DAY = "COLUMN_DAY";
    public static final String COLUMN_IS_DONE = "COLUMN_IS_DONE";

    public MyChallengesTable(@Nullable Context context) {
        super(context, "mychallenge.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatment = "CREATE TABLE " + MY_CHALLENGES_TABLE + " ( "
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CHALLENGE_ID + " INTEGER, "
                    + COLUMN_DAY + " INTEGER, "
                    + COLUMN_IS_DONE + "  INTEGER)";

        db.execSQL(createTableStatment);
    }

    public boolean addChallenge(ChallengeTrack ch) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (int i = 0; i < ch.getDayTrack().size() ; i++) {
            cv.put(COLUMN_CHALLENGE_ID, ch.getChallenge().getId());
            cv.put(COLUMN_DAY, i + 1);
            cv.put(COLUMN_IS_DONE, !ch.getDayTrack().get(i+1) ? 0 : 1);

            long insert = db.insert(MY_CHALLENGES_TABLE, null, cv);

            if(insert == -1) {
                db.close();
                return false;
            }
        }
        db.close();
        return true;
    }

    public List<ChallengeTrack> getAllChallenges() {
        List<ChallengeTrack> myChallengesTrackList = new ArrayList<ChallengeTrack>();
        String queryString = "SELECT * FROM " + MY_CHALLENGES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        Hashtable<Integer, Boolean> dayTrack;
        Hashtable<Integer, Hashtable<Integer, Boolean>> challengeTrack = new Hashtable<>();


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                int cursorID = cursor.getInt(0);
                int cursorChallengeID = cursor.getInt(1);
                int cursorDay = cursor.getInt(2);
                boolean cursorIsDone = cursor.getInt(3) == 1;


                if(challengeTrack.containsKey(cursorChallengeID)){ // If challenges exits
                    Hashtable<Integer, Boolean> ch = challengeTrack.get(cursorChallengeID);
                    if (ch == null) throw new AssertionError();
                    ch.put(cursorDay,cursorIsDone);
                }
                else{
                    dayTrack = new Hashtable<>();
                    dayTrack.put(cursorDay,cursorIsDone);
                    challengeTrack.put(cursorChallengeID, dayTrack); // else it does not -> add new one
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();

        // TODO init myChallengesList

        Set<Integer> setOfKeys = challengeTrack.keySet();
        // for-each loop
        for(Integer key : setOfKeys) {
            Challenge challenge = getChallengeData(key);
            Hashtable<Integer, Boolean> track = challengeTrack.get(key);
            ChallengeTrack chTrack = new ChallengeTrack(challenge, track);
            myChallengesTrackList.add(chTrack);
        }
        return myChallengesTrackList;
    }

    public Challenge getChallengeData(int challengeId) {
        List<Challenge> challengesData = ChallengeModel.instance.getChallengesList();
        for (Challenge ch: challengesData) {
            if (ch.getId() == challengeId)
                return ch;
        }
        return null;
    }

    public boolean isChallengeRegistered(int challengeId) {

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + MY_CHALLENGES_TABLE + " where " + COLUMN_CHALLENGE_ID + " = " + challengeId;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }
    public boolean clearTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ MY_CHALLENGES_TABLE);
        db.close();
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+ MY_CHALLENGES_TABLE);
//        onCreate(db);
    }

    public boolean setMyChallenge(ChallengeTrack dayTrack) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int challengeId = dayTrack.getChallenge().getId();

        for (int i = 0; i < dayTrack.getDayTrack().size() ; i++) {
            Integer currDay = dayTrack.getDayTrackList().get(i).getDay();
            Integer  currIsChecked = dayTrack.getDayTrackList().get(i).getChecked() ? 1 : 0;

            cv.put(COLUMN_CHALLENGE_ID, challengeId);
            cv.put(COLUMN_DAY, currDay);
            cv.put(COLUMN_IS_DONE, currIsChecked);

//            db.update(MY_CHALLENGES_TABLE, cv,
//                        COLUMN_CHALLENGE_ID + "= ? AND" + COLUMN_DAY + " = ? ",
//                         new String[]{String.valueOf(challengeId) , String.valueOf(currDay)});

            String strSQL = "UPDATE "+ MY_CHALLENGES_TABLE +
                    " SET " + COLUMN_IS_DONE + " = " + currIsChecked +
                    " WHERE " + COLUMN_CHALLENGE_ID + " = " + String.valueOf(challengeId) +
                    " AND " + COLUMN_DAY + " = " + String.valueOf(currDay);

            db.execSQL(strSQL);


//            long insert = db.insert(MY_CHALLENGES_TABLE, null, cv);

//            if(insert == -1) {
//                db.close();
//                return false;
//            }
        }
        db.close();
        return true;
    }
}
