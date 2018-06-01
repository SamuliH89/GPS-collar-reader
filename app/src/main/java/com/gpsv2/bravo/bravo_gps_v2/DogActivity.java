package com.gpsv2.bravo.bravo_gps_v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DogActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Button button;
    ImageView imageView2;
    Button buttonImage;
    EditText edit1, edit2,edit3;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private RatingBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit1 = (EditText) findViewById(R.id.editText6);
        edit2 = (EditText) findViewById(R.id.editText);
        edit3 = (EditText) findViewById(R.id.editText2);

        bar = (RatingBar) findViewById(R.id.ratingBar);
        button = (Button) findViewById(R.id.button5);
        buttonImage = (Button) findViewById(R.id.button8);
        imageView2 = (ImageView) findViewById(R.id.imageView3);



        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();


            }
        });

        edit1.addTextChangedListener(DogTextWatcher);
        edit2.addTextChangedListener(DogTextWatcher);
        edit3.addTextChangedListener(DogTextWatcher);

    }

    private void openGallery() {
       Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView2.setImageURI(imageUri);
        }


    }

    public void save(View v){

        String name = edit1.getText().toString();
        String birth = edit2.getText().toString();
        String race = edit3.getText().toString();
        String barValue = String.valueOf(bar.getRating());

        Map<String, String> note = new HashMap<>();
        note.put("NAME", name);
        note.put("BIRTH", birth);
        note.put("RACE", race);
        note.put("RATING VALUE", barValue);

        db.collection("Dog data").add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(DogActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DogActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private TextWatcher DogTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String nameInput = edit1.getText().toString().trim();
            String birthInput = edit2.getText().toString().trim();
            String raceInput = edit3.getText().toString().trim();

            button.setEnabled(!nameInput.isEmpty() && !birthInput.isEmpty() && !raceInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
