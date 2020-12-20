package com.example.challengeyourself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button addChallengeButton = view.findViewById(R.id.main_fragment_addchallenge_button);
        addChallengeButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_addChallengeFragment));

        return view;
    }
}