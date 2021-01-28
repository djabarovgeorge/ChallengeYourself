package com.example.challengeyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.challengeyourself.ChallengeModel.ChallengeModel;
import com.example.challengeyourself.ChallengeModel.MyChallengeModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChallengeModel.instance.initDatabase(this);
        MyChallengeModel.instance.initDatabase(this);

        // clean my challenges table
//        MyChallengeModel.instance.clearTable();
    }
}