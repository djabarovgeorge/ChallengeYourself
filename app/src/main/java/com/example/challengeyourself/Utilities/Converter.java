package com.example.challengeyourself.Utilities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.challengeyourself.ChallengeModel.DayTrack;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Converter {
    public ArrayList<DayTrack> FromHashToObjectList(Hashtable<Integer,Boolean> listCheck)
    {
        ArrayList<DayTrack> trackList = new ArrayList<>();
        // getting keySet() into Set
        Set<Integer> trackDay = listCheck.keySet();

        // for-each loop
        for(Integer day : trackDay) {
            DayTrack item = new DayTrack(day, listCheck.get(day));
            trackList.add(item);
        }

        return trackList;
    }

    public Hashtable<Integer,Boolean> FromObjectListToHash(ArrayList<DayTrack> listCheck)
    {
        Hashtable<Integer,Boolean> trackList = new Hashtable<Integer,Boolean>();

        // for-each loop
        for(DayTrack day : listCheck) {
            trackList.put(day.getDay(), day.getChecked());
        }

        return trackList;
    }

    public static byte[] ImageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
