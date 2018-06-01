package com.gpsv2.bravo.bravo_gps_v2;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AlertsActivity extends AppCompatActivity {

    private CheckBox cbox, cbox2;
    private Button button;
    private EditText edit1, edit2;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cbox = (CheckBox) findViewById(R.id.checkBox);
        cbox2 = (CheckBox) findViewById(R.id.checkBox2);
        button = (Button) findViewById(R.id.button10);

        edit1 = (EditText) findViewById(R.id.editText7);
        edit2 = (EditText) findViewById(R.id.editWhere);

    }

    public void save(View v){

        String when = edit1.getText().toString();
        String where = edit2.getText().toString();
        String checkBox1Value = String.valueOf(cbox.isChecked());
        String checkBox2Value = String.valueOf(cbox2.isChecked());


        Map<String, String> note = new HashMap<>();
        note.put("WHEN", when);
        note.put("WHERE", where);
        note.put("DOG MISSING", checkBox1Value);
        note.put("COLLAR MISSING", checkBox2Value);


        db.collection("Missing").add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AlertsActivity.this, "Published.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AlertsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });


    }




}
