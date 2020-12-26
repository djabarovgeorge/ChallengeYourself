package com.example.challengeyourself.MyChallenges;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challengeyourself.ChallengeInfoArgs;
import com.example.challengeyourself.ChallengeItemAdapter;
import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;
import com.example.challengeyourself.ChallengeModel.ChallengeTrack;
import com.example.challengeyourself.ChallengeModel.MyChallengeModel;
import com.example.challengeyourself.MainActivity;
import com.example.challengeyourself.R;

import java.util.List;

public class MyChallengeTrackDetails extends Fragment {

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_challenge_track_details, container, false);

        assert getArguments() != null;
        String idString = ChallengeInfoArgs.fromBundle(getArguments()).getChallengeId();
        final int idInteger = Integer.parseInt(idString);

        TextView tvChallengeName = view.findViewById(R.id.mychallenges_track_d_name);
        TextView tvChallengeDetails = view.findViewById(R.id.mychallenges_track_d_detailes);
        LinearLayout linearLayout  = view.findViewById(R.id.rootContainer);
//        ListView list = view.findViewById(R.id.my_challenges_day_track_list);

        final ChallengeTrack myChallengeFromDB = MyChallengeModel.instance.getChallengeTrackById(idInteger);

        tvChallengeName.setText(myChallengeFromDB.getChallenge().getName());
        tvChallengeDetails.setText(myChallengeFromDB.getChallenge().getFreeText());



        // Create Checkbox Dynamically
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setText("BOX Check");
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                               ViewGroup.LayoutParams.MATCH_PARENT));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String msg = "You have " + (isChecked ? "checked" : "unchecked") + " this Check it Checkbox.";
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Add Checkbox to LinearLayout
        if (linearLayout != null) {
            linearLayout.addView(checkBox);
        }





////        List<CheckBox> challengeListFromDB = ChallengeModel.instance.getChallengesList();
//        List<CheckBox> checkBoxList = new List<>();
//
//        ChallengeItemAdapter adapter = new ChallengeItemAdapter(getContext(), R.layout.challenge_list_adapter, challengeListFromDB);
//        list.setAdapter(adapter);




//        // Create Checkbox Dynamically
//        CheckBox checkBox = new CheckBox(getContext());
//        checkBox.setText("BOX BOX CHECK");
//        checkBox.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                String msg = "You have " + (isChecked ? "checked" : "unchecked") + " this Check it Checkbox.";
//                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Add Checkbox to LinearLayout
//        if (list != null) {
//            list.addView(checkBox);
//        }





        return view;
    }
}