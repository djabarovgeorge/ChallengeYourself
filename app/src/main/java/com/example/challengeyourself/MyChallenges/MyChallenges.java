package com.example.challengeyourself.MyChallenges;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.challengeyourself.MenuChallengesListFragment;
import com.example.challengeyourself.R;


public class MyChallenges extends Fragment {

    public MyChallenges() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_challenges, container, false);

        // Add MenuChallengeListFragment to Menu page
        Fragment childFragment = new MyChallengesListFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mychallenges_child_list_framelayout, childFragment).commit();
        return view;
    }
}