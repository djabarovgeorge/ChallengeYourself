package com.example.challengeyourself.MyChallenges;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.challengeyourself.ChallengeInfoArgs;
import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;
import com.example.challengeyourself.ChallengeModel.ChallengeTrack;
import com.example.challengeyourself.ChallengeModel.DayTrack;
import com.example.challengeyourself.ChallengeModel.MyChallengeModel;
import com.example.challengeyourself.MenuChallengesListFragment;
import com.example.challengeyourself.R;
import com.example.challengeyourself.Utilities.Convertor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyChallengeTrackDetails extends Fragment {


    private ArrayList<DayTrack> trackList;
    private Convertor convertor = new Convertor();
    private ChallengeTrack myChallengeFromDB;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_challenge_track_details, container, false);

        assert getArguments() != null;
        String idString = ChallengeInfoArgs.fromBundle(getArguments()).getChallengeId();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();


        myChallengeFromDB = MyChallengeModel.instance.getChallengeTrackById(Integer.parseInt(idString));

        ListView track_list_view = view.findViewById(R.id.fragment_my_challenge_list_view);

        trackList = convertor.FromHashToObjectList(myChallengeFromDB.getDayTrack());

        Collections.sort(trackList);

        MyChallengeTrackDetailsAdapter adapter = new MyChallengeTrackDetailsAdapter(getContext(), R.layout.fragment_my_challenge_track_details, trackList);

        track_list_view.setAdapter(adapter);



        Button saveBtn = view.findViewById(R.id.fragment_my_challenges_track_save_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add new challenge to db
//                ChallengeModel.instance.addChallenge(new Challenge(nameView.getText().toString(), Integer.parseInt(durationView.getText().toString()),descriptionView.getText().toString()));
//                Toast.makeText(getActivity(), "Challenge was added to the list", Toast.LENGTH_LONG).show();
//                Navigation.findNavController(view).popBackStack();

                // update local list
                myChallengeFromDB.setDayTrackList(trackList);

                // update db
                MyChallengeModel.instance.setMyChallenge(myChallengeFromDB);
            }
        });


        track_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "the position is: " + String.valueOf(position));
            }
        });
        return view;
    }
}