package com.example.challengeyourself;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.challengeyourself.ChallengeModel.Challenge;

import java.util.ArrayList;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class MenuChallengesListFragment extends Fragment {
    private static final String TAG = "TAG - Menu_C_L_F";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_list_adapter, container, false);

        ListView list = view.findViewById(R.id.challenge_list_adapter_v);

        ChallengeItemAdapter adapter = new ChallengeItemAdapter(getContext(), R.layout.challenge_list_adapter);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "the position is: " + String.valueOf(position));
                NavDirections action = MenuFragmentDirections.actionMenuFragmentToChallengeInfo(String.valueOf(position));
                assert getParentFragment() != null;
                findNavController(getParentFragment()).navigate(action);

            }
        });

        return view;
    }
}

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//@Override
//public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.d(TAG, "the position is: " + String.valueOf(position));
//        }
//        });