package com.example.challengeyourself.MyChallenges;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.R;

import java.util.List;

public class ChallengeTrackAdapter extends ArrayAdapter<CheckBox> {
    private static final String TAG = "TAG - Challenge_I_A";

    //    private int lastPosition = -1;
    private Context mContext;
    private int mResource;

    public List<CheckBox> checkBoxList;

    public ChallengeTrackAdapter(Context context, int resource, List<CheckBox> list) {
        super(context, resource);
        checkBoxList = list;
        mContext = context;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return this.checkBoxList.size();
    }

    @Override
    public CheckBox getItem(int position) {
        return checkBoxList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return checkBoxList.get(position).getId();
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

        Log.d(TAG, checkBoxList.get(position).toString());


        //Extract data from database
//        String getNameFromDB = checkBoxList.get(position).getName();

        // TODO set View item - need to add more logic to here - now will update only the text
        TextView tv = view.findViewById(R.id.challenge_item_text);
        tv.setText(String.valueOf(position));

        return view;
    }
}
