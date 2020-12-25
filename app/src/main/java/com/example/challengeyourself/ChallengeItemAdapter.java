package com.example.challengeyourself;

import androidx.annotation.NonNull;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;

import java.util.ArrayList;
import java.util.List;

class ChallengeItemAdapter extends ArrayAdapter<Challenge> {
    private static final String TAG = "TAG - Challenge_I_A";

//    private int lastPosition = -1;
    private Context mContext;
    private int mResource;

    public List<Challenge> challengeListFromDB;

    public ChallengeItemAdapter(Context context, int resource) {
        super(context, resource);
        challengeListFromDB = ChallengeModel.instance.getChallengesList();
        mContext = context;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return this.challengeListFromDB.size();
    }

    @Override
    public Challenge getItem(int position) {
        return challengeListFromDB.get(position);
    }

    @Override
    public long getItemId(int position) {
        return challengeListFromDB.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.challenge_item , parent, false);
        }
//        Log.d(TAG, view.toString());
        Log.d(TAG, "test");

        Log.d(TAG, challengeListFromDB.get(position).toString());


        //Extract data from database
        String getNameFromDB = challengeListFromDB.get(position).getName();

        // TODO set View item - need to add more logic to here - now will update only the text
        TextView tv = view.findViewById(R.id.challenge_item_text);
        tv.setText(getNameFromDB);

        return view;
    }
}
