package com.example.challengeyourself;
/*Assigment:3
Campus:Ashdod
Author:Anna Rogojine 323686477
Author:George Djabarov 321335531
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;

import java.util.List;

public class ChallengeItemAdapter extends ArrayAdapter<Challenge> {
    private static final String TAG = "TAG - Challenge_I_A";

//    private int lastPosition = -1;
    private Context mContext;
    private int mResource;

    public List<Challenge> challengeListFromDB;
    private Challenge listFromDB;

    public ChallengeItemAdapter(Context context, int resource, List<Challenge> list) {
        super(context, resource);
        challengeListFromDB = list;
        mContext = context;
        mResource = resource;
    }
    public void updateChallengeList(List<Challenge> newlist) {
        challengeListFromDB.clear();
        challengeListFromDB.addAll(newlist);
        this.notifyDataSetChanged();
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
        listFromDB = challengeListFromDB.get(position);

        // TODO set View item - need to add more logic to here - now will update only the text
        TextView textView = view.findViewById(R.id.challenge_item_text);
        ImageView imageView = view.findViewById(R.id.challenge_item_image);

        textView.setText(listFromDB.getName());

        Bitmap bitmap = BitmapFactory.decodeByteArray(listFromDB.getImage(), 0 , listFromDB.getImage().length);
        imageView.setImageBitmap(bitmap);

        return view;
    }
}
