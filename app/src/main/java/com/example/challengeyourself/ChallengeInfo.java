package com.example.challengeyourself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;

import java.util.List;

public class ChallengeInfo extends Fragment {
    private static final String TAG = "TAG-Challenge_I";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challenge_info, container, false);

        List<Challenge> challengeListFromDB = ChallengeModel.instance.getChallengesList();

        String text = ChallengeInfoArgs.fromBundle(getArguments()).getChallengeId();
        TextView tvId = view.findViewById(R.id.challenge_info_text_v);
        TextView tvDescription = view.findViewById(R.id.challenge_info_description_text);

        tvId.setText(text);
        tvDescription.setText(challengeListFromDB.get(Integer.parseInt(text)).getFreeText());

        return view;
    }

}