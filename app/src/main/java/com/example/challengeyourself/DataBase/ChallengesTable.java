package com.example.challengeyourself.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.challengeyourself.ChallengeModel.Challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengesTable extends SQLiteOpenHelper {

    public static final String CHALLENGES_TABLE = "CHALLENGES_TABLE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_NUMBER_OF_DAYS = "NUMBER_OF_DAYS";
    public static final String COLUMN_FREE_TEXT = "FREE_TEXT";
    public static final String COLUMN_ID = "COLUMN_ID";



    public ChallengesTable(@Nullable Context context) {
        super(context, "challenge.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatment = "CREATE TABLE " + CHALLENGES_TABLE + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NAME + " TEXT, "
                        + COLUMN_NUMBER_OF_DAYS + " INTEGER, "
                        + COLUMN_FREE_TEXT + " TEXT)";

        db.execSQL(createTableStatment);
    }

    public boolean addChallenge(Challenge ch) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, ch.getName());
        cv.put(COLUMN_NUMBER_OF_DAYS, ch.getNumberOfDays());
        cv.put(COLUMN_FREE_TEXT, ch.getFreeText());

        long insert = db.insert(CHALLENGES_TABLE, null, cv);
        return insert != -1;
    }

    public List<Challenge> getAllChallenges() {
        List<Challenge> challengesList = new ArrayList<Challenge>();
        String queryString = "SELECT * FROM "+ CHALLENGES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                int cursorID = cursor.getInt(0);
                String cursorName = cursor.getString(1);
                int cursorNumberOfDays = cursor.getInt(2);
                String cursorFreeText = cursor.getString(3);

                Challenge challengeItem = new Challenge(cursorID, cursorName, cursorNumberOfDays, cursorFreeText);
                challengesList.add(challengeItem);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return challengesList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

