package com.example.challengeyourself;
/*Assigment:3
Campus:Ashdod
Author:Anna Rogojine 323686477
Author:George Djabarov 321335531
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.challengeyourself.ChallengeModel.ChallengeModel;
import com.example.challengeyourself.ChallengeModel.MyChallengeModel;

public class MainActivity extends AppCompatActivity {

    public static Context _contextOfApplication;

    public static Context getContextOfApplication()
    {
        return _contextOfApplication;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _contextOfApplication = getApplicationContext();

        ChallengeModel.instance.initDatabase(this);
        MyChallengeModel.instance.initDatabase(this);

        // clean my challenges table
        ChallengeModel.instance.clearTable();
        MyChallengeModel.instance.clearTable();
    }
}