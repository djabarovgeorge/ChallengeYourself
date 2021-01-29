package com.example.challengeyourself;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;

import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class MenuChallengesListFragment extends Fragment {
    private static final String TAG = "TAG - Menu_C_L_F";
    private List<Challenge> challengeListFromDB;
    private ChallengeItemAdapter adapter;
    private View _view;
    private LayoutInflater _inflater;
    private ViewGroup _container;
    private ListView _list;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _container = container;
        _inflater = inflater;
        _view = inflater.inflate(R.layout.challenge_list_adapter, container, false);

        _list = _view.findViewById(R.id.challenge_list_adapter_v);

        challengeListFromDB = ChallengeModel.instance.getChallengesList();
        adapter = new ChallengeItemAdapter(getContext(), R.layout.challenge_list_adapter, challengeListFromDB);
        _list.setAdapter(adapter);


        _list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "the position is: " + String.valueOf(position));
                NavDirections action = MenuFragmentDirections.actionMenuFragmentToChallengeInfo(String.valueOf(position));
                assert getParentFragment() != null;
                findNavController(getParentFragment()).navigate(action);

            }
        });


        _list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item ==1) {
                            // delete the from db
                            int challengeId =  challengeListFromDB.get(position).getId();
                            showDialogDelete(challengeId);
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
        return _view;
    }



    private void showDialogDelete(final int idChallenge){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(getContext());

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    // Delete from the db
                    ChallengeModel.instance.deleteChallenge(idChallenge);
                    Toast.makeText(getContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();


                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }

                // Update local list
                challengeListFromDB = ChallengeModel.instance.getChallengesList();

                //Update adapter
                adapter.updateChallengeList(challengeListFromDB);
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

}