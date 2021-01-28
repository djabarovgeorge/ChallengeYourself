package com.example.challengeyourself.MyChallenges;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.challengeyourself.ChallengeModel.DayTrack;
import com.example.challengeyourself.R;

import java.util.List;

public class MyChallengeTrackDetailsAdapter extends ArrayAdapter<DayTrack> {

    private static final String TAG = "TAG - Day_Track_Ad";
    private final Context mContext;
    private List<DayTrack> _dayTrackList;


    public MyChallengeTrackDetailsAdapter(Context context, int resource, List<DayTrack> dayTrackList) {
        super(context, resource);
        _dayTrackList = dayTrackList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return this._dayTrackList.size();
    }

    @Override
    public DayTrack getItem(int position) {
        return _dayTrackList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _dayTrackList.get(position).getDay();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.fragment_my_chllenges_track_row , parent, false);
        }
        view.setClickable(true);

        Log.d(TAG, _dayTrackList.get(position).toString());

        //Extract data from database
        Integer getDay = _dayTrackList.get(position).getDay();
        Boolean getIsChecked = _dayTrackList.get(position).getChecked();


        // TODO set View item - need to add more logic to here - now will update only the text
        CheckBox trackCheckBoxView = view.findViewById(R.id.my_challenge_row_track_checkbox);
        TextView trackTextView = view.findViewById(R.id.my_challenge_row_track_text);

        trackCheckBoxView.setChecked(getIsChecked);
        trackTextView.setText(String.valueOf(getDay));


        trackCheckBoxView.setTag(position); // day value

        trackCheckBoxView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer positionCheckBox = Integer.parseInt(view.getTag().toString());
                boolean newState = !_dayTrackList.get(positionCheckBox).getChecked();
                _dayTrackList.get(positionCheckBox).setChecked(newState);
            }
        });

        return view;
    }
}
