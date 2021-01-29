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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.challengeyourself.ChallengeInfoArgs;
import com.example.challengeyourself.ChallengeModel.ChallengeTrack;
import com.example.challengeyourself.ChallengeModel.DayTrack;
import com.example.challengeyourself.ChallengeModel.MyChallengeModel;
import com.example.challengeyourself.R;
import com.example.challengeyourself.Utilities.Converter;

import java.util.ArrayList;
import java.util.Collections;

public class MyChallengeTrackDetails extends Fragment {


    private ArrayList<DayTrack> trackList;
    private Converter convertor = new Converter();
    private ChallengeTrack myChallengeFromDB;
    private TextView _challengeNameTextView;
    private TextView _challengeDescriptionTextView;
    private ListView _track_list_view;
    private View _view;
    private Button _saveBtn;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Get views
        _view = inflater.inflate(R.layout.fragment_my_challenge_track_details, container, false);
        _challengeNameTextView = _view.findViewById(R.id.mychallenges_track_d_name);
        _challengeDescriptionTextView = _view.findViewById(R.id.mychallenges_track_d_detailes);
        _track_list_view = _view.findViewById(R.id.fragment_my_challenge_list_view);
        _saveBtn = _view.findViewById(R.id.fragment_my_challenges_track_save_btn);


        // Get current challenge from bundle
        assert getArguments() != null;
        String idString = ChallengeInfoArgs.fromBundle(getArguments()).getChallengeId();

//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Get current challenge from db
        myChallengeFromDB = MyChallengeModel.instance.getChallengeTrackById(Integer.parseInt(idString));

        // Set views
        _challengeNameTextView.setText(myChallengeFromDB.getChallenge().getName());
        _challengeDescriptionTextView.setText(myChallengeFromDB.getChallenge().getFreeText());
        trackList = convertor.FromHashToObjectList(myChallengeFromDB.getDayTrack());
        Collections.sort(trackList);

        // Set adapter to init each track data
        MyChallengeTrackDetailsAdapter adapter = new MyChallengeTrackDetailsAdapter(getContext(), R.layout.fragment_my_challenge_track_details, trackList);
        _track_list_view.setAdapter(adapter);

        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update local list
                myChallengeFromDB.setDayTrackList(trackList);

                // update db
                MyChallengeModel.instance.setMyChallenge(myChallengeFromDB);

//                Navigation.findNavController(view).popBackStack();
            }
        });


        _track_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "the position is: " + String.valueOf(position));
            }
        });
        return _view;
    }
}