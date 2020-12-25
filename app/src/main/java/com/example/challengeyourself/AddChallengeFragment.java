package com.example.challengeyourself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;

public class AddChallengeFragment extends Fragment {

    EditText nameView, descriptionView, durationView;
    Button btnSave;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);

        nameView = view.findViewById(R.id.add_challenge_name_v);
        descriptionView = view.findViewById(R.id.add_challenge_description_v);
        durationView = view.findViewById(R.id.add_challenge_duration_v);
        btnSave = view.findViewById(R.id.add_challenge_save_btn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add new challenge to db
                ChallengeModel.instance.addChallenge(new Challenge(nameView.getText().toString(), Integer.parseInt(durationView.getText().toString()),descriptionView.getText().toString()));
                Navigation.findNavController(view).popBackStack();
            }
        });



        return view;
    }
}