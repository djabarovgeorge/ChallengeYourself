package com.example.challengeyourself;
/*Assigment:3
Campus:Ashdod
Author:Anna Rogojine 323686477
Author:George Djabarov 321335531
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.challengeyourself.ChallengeModel.Challenge;
import com.example.challengeyourself.ChallengeModel.ChallengeModel;
import com.example.challengeyourself.Utilities.Converter;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static com.example.challengeyourself.Utilities.Utilities.IsNullOrEmpty;

public class AddChallengeFragment extends Fragment {

    final int REQUEST_CODE_GALLERY = 999;
    EditText nameView, descriptionView, durationView;
    Button btnSave;
    private Button addImageBtn;
    private final Converter _converter = new Converter();
    Context applicationContext = MainActivity.getContextOfApplication();
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);

        nameView = view.findViewById(R.id.add_challenge_name_v);
        descriptionView = view.findViewById(R.id.add_challenge_description_v);
        durationView = view.findViewById(R.id.add_challenge_duration_v);
        addImageBtn = view.findViewById(R.id.add_challenge_add_image_btn);
        btnSave = view.findViewById(R.id.add_challenge_save_btn);
        imageView = view.findViewById(R.id.add_challenge_image_view);

        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add new challenge to db
                String challengeName = !IsNullOrEmpty(nameView.getText().toString()) ? nameView.getText().toString() : "";
                int duration = !IsNullOrEmpty(durationView.getText().toString()) ? Integer.parseInt(durationView.getText().toString()) : -1;
                String description = !IsNullOrEmpty(descriptionView.getText().toString()) ? descriptionView.getText().toString() : "";
                byte[] image = Converter.ImageViewToByte(imageView);

                ChallengeModel.instance.addChallenge(new Challenge(challengeName, duration,description, image));
                Toast.makeText(getActivity(), "Challenge was added to the list", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                assert uri != null;
                InputStream inputStream = applicationContext.getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}