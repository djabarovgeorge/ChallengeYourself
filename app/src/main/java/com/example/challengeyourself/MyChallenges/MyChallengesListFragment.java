package com.example.challengeyourself.MyChallenges;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.example.challengeyourself.ChallengeItemAdapter;
import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeTrack;
import com.example.challengeyourself.ChallengeModel.MyChallengeModel;
import com.example.challengeyourself.MenuFragmentDirections;
import com.example.challengeyourself.R;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static com.example.challengeyourself.MenuFragmentDirections.actionMenuFragmentToChallengeInfo;

public class MyChallengesListFragment extends Fragment {

    List<Challenge> challengeList;
    private List<ChallengeTrack> myChallengeListFromDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_list_adapter, container, false);

        ListView list = view.findViewById(R.id.challenge_list_adapter_v);

        myChallengeListFromDB = MyChallengeModel.instance.getChallengesList();

        // Create Challenge list for view
        challengeList = new ArrayList<>();
        for (ChallengeTrack ct: myChallengeListFromDB) {
            challengeList.add(ct.getChallenge()); }

        ChallengeItemAdapter adapter = new ChallengeItemAdapter(getContext(), R.layout.challenge_list_adapter, challengeList);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "the position is: " + String.valueOf(position));

                // get challenge id from position
                int challengeId = challengeList.get(position).getId();

                NavDirections action = MyChallengesDirections.actionMyChallengesToMyChallengeTrackDetails(String.valueOf(challengeId));
                assert getParentFragment() != null;
                findNavController(getParentFragment()).navigate(action);
            }
        });

        return view;
    }
}