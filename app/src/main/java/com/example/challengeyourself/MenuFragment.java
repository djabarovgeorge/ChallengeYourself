package com.example.challengeyourself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Add MenuChallengeListFragment to Menu page
        Fragment childFragment = new MenuChallengesListFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.menu_child_challenge_list_v, childFragment).commit();

        Button myChallenges = view.findViewById(R.id.menu_fragment_mychallenges_btn);
        myChallenges.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_myChallenges));


        Button addChallengeButton = view.findViewById(R.id.main_fragment_addchallenge_button);
        addChallengeButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_addChallengeFragment));



        return view;
    }
}