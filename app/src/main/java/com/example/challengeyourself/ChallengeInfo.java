package com.example.challengeyourself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;
import com.example.challengeyourself.ChallengeModel.ChallengeTrack;
import com.example.challengeyourself.ChallengeModel.MyChallengeModel;
import com.example.challengeyourself.MyChallenges.MyChallenges;

import java.util.List;

public class ChallengeInfo extends Fragment {
    private static final String TAG = "TAG-Challenge_I";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_challenge_info, container, false);

        final List<Challenge> challengeListFromDB = ChallengeModel.instance.getChallengesList();


        String idString = ChallengeInfoArgs.fromBundle(getArguments()).getChallengeId();
        final int idInteger = Integer.parseInt(idString);
        Log.d(TAG ,"text: " + idString);

        TextView tvId = view.findViewById(R.id.challenge_info_text_v);
        TextView tvDescription = view.findViewById(R.id.challenge_info_description_text);
        TextView tvDuration = view.findViewById(R.id.challenge_info_duration_text);

        String chIdString = String.valueOf(challengeListFromDB.get(idInteger).getId());
        String chDurationString = String.valueOf(challengeListFromDB.get(idInteger).getNumberOfDays());

        tvId.setText(chIdString);
        tvDescription.setText(challengeListFromDB.get(idInteger).getFreeText());
        tvDuration.setText(chDurationString);

        Button saveToMyChallengesBtn = view.findViewById(R.id.challenge_info_save_btn);


        saveToMyChallengesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add new challenge to db
                int challengeIndex = idInteger +1;
                if(MyChallengeModel.instance.isChallengeRegistered(challengeIndex)) {
                    Toast.makeText(getActivity(), "This challenge is already registered", Toast.LENGTH_LONG).show();
                }
                else {
                    MyChallengeModel.instance.addToMyChallenge(new ChallengeTrack(challengeListFromDB.get(idInteger)));
                    Toast.makeText(getActivity(), "Challenge was added.", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).popBackStack();
                }
            }
        });
        return view;
    }
}