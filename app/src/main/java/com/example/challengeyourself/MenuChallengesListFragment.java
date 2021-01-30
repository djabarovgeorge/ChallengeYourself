package com.example.challengeyourself;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;

import java.util.ArrayList;

import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;
/*Assigment:3
Campus:Ashdod
Author:Anna Rogojine 323686477
Author:George Djabarov 321335531
 */

public class MenuChallengesListFragment extends Fragment {
    private static final String TAG = "TAG - Menu_C_L_F";
    private List<Challenge> challengeListFromDB;
    private ChallengeItemAdapter adapter;
    private View _view;
    private LayoutInflater _inflater;
    private ViewGroup _container;
    private ListView _list;
    private ImageView imageViewChallenge;

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
                        if (item == 1) {
                            // delete the from db
                            int challengeId =  challengeListFromDB.get(position).getId();
                            showDialogDelete(challengeId);
                        }
                        else{
                            // update
                            int challengeId =  challengeListFromDB.get(position).getId();
                            // show dialog update at here
                            showDialogUpdate(getActivity(), challengeId);
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
        return _view;
    }

    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_challenge_activity);
        dialog.setTitle("Update");

        imageViewChallenge = (ImageView) dialog.findViewById(R.id.imageViewChallenge);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtDays = (EditText) dialog.findViewById(R.id.edtDays);
        final EditText edtDisc = (EditText) dialog.findViewById(R.id.edtDisc);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });


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