package com.gpsv2.bravo.bravo_gps_v2;

import android.content.DialogInterface;
import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button button;
    private Button buttonDetails;
    private Button buttonDog;
    private Button buttonAlert;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_first);

        button = (Button) findViewById(R.id.button2);
        buttonDetails = (Button) findViewById(R.id.button4);
        buttonDog = (Button) findViewById(R.id.button6);
        buttonAlert = (Button) findViewById(R.id.button9);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActity2();
            }
        });

        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActity3();
            }
        });

        buttonDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActity4();
            }
        });

        buttonAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActity5();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActity6();
            }
        });


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setMessage(getString(R.string.exitMsg))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirstActivity.this.finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();

    }

    public void openActity2(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openActity3(){

        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    public void openActity4(){

        Intent intent = new Intent(this, DogActivity.class);
        startActivity(intent);
    }

    public void openActity5(){

        Intent intent = new Intent(this, AlertsActivity.class);
        startActivity(intent);
    }

    public void openActity6(){

        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

}
