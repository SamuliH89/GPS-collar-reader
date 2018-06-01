package com.gpsv2.bravo.bravo_gps_v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Button button;
    ImageView imageView;
    Button buttonImage;
    EditText edit1, edit2,edit3,edit4;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit1 = (EditText) findViewById(R.id.editText3);
        edit2 = (EditText) findViewById(R.id.editText2);
        edit3 = (EditText) findViewById(R.id.editText4);
        edit4 = (EditText) findViewById(R.id.editText5);

        button = (Button) findViewById(R.id.button3);
        buttonImage = (Button) findViewById(R.id.button7);
        imageView = (ImageView) findViewById(R.id.imageView2);



        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();

            }
        });

        edit1.addTextChangedListener(DetailsTextWatcher);
        edit2.addTextChangedListener(DetailsTextWatcher);
        edit3.addTextChangedListener(DetailsTextWatcher);
        edit4.addTextChangedListener(DetailsTextWatcher);

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
            imageView.setImageURI(imageUri);
        }

    }

    public void save(View v){

        String name = edit1.getText().toString();
        String birth = edit2.getText().toString();
        String address = edit3.getText().toString();
        String phone = edit4.getText().toString();

        Map<String, String> note = new HashMap<>();
        note.put("NAME", name);
        note.put("BIRTH", birth);
        note.put("ADDRESS", address);
        note.put("PHONE", phone);

        db.collection("Owners").add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(DetailsActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private TextWatcher DetailsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String nameInput = edit1.getText().toString().trim();
            String birthInput = edit2.getText().toString().trim();
            String addressInput = edit3.getText().toString().trim();
            String phoneInput = edit4.getText().toString().trim();

            button.setEnabled(!nameInput.isEmpty() && !birthInput.isEmpty() && !addressInput.isEmpty() && !phoneInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}
