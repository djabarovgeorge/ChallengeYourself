package com.example.challengeyourself;
/*Assigment:3
Campus:Ashdod
Author:Anna Rogojine 323686477
Author:George Djabarov 321335531
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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


        assert getArguments() != null;
        String idString = ChallengeInfoArgs.fromBundle(getArguments()).getChallengeId();
        final int idInteger = Integer.parseInt(idString);
        Log.d(TAG ,"text: " + idString);

        TextView tvId = view.findViewById(R.id.challenge_info_text_v);
        TextView tvDescription = view.findViewById(R.id.challenge_info_description_text);
        TextView tvDuration = view.findViewById(R.id.challenge_info_duration_text);
        ImageView imageView = view.findViewById(R.id.challenge_info_image_view);


        String chNameString = String.valueOf(challengeListFromDB.get(idInteger).getName());
        String chDescriptionString = String.valueOf(challengeListFromDB.get(idInteger).getFreeText());
        String chDurationString = String.valueOf(challengeListFromDB.get(idInteger).getNumberOfDays());
        byte[] imageByte = challengeListFromDB.get(idInteger).getImage();

        tvId.setText(chNameString);
        tvDescription.setText(chDescriptionString);
        tvDuration.setText(chDurationString);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0 , imageByte.length);
        imageView.setImageBitmap(bitmap);

        Button addMyChallengesBtn = view.findViewById(R.id.challenge_info_save_btn);


        addMyChallengesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add new challenge to db
                int challengeIndex = idInteger +1;
                if(MyChallengeModel.instance.isChallengeRegistered(challengeIndex)) {
                    Toast.makeText(getActivity(), "This challenge is already registered", Toast.LENGTH_LONG).show();
                }
                else {
                    MyChallengeModel.instance.addToMyChallenge(new ChallengeTrack(ChallengeModel.instance.getChallenge(challengeIndex)));
                    Toast.makeText(getActivity(), "Challenge was added.", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).popBackStack();
                }
            }
        });
        return view;
    }
}