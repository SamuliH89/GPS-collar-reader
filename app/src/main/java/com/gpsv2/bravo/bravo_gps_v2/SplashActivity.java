package com.gpsv2.bravo.bravo_gps_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageview = (ImageView) findViewById(R.id.rotate);
        TextView textview = (TextView) findViewById(R.id.text);



        Animation animRotate= AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotateanim);
        Animation fade_in = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
        Animation fade_out = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);

        AnimationSet s = new AnimationSet(false);
        s.addAnimation(fade_in);

        animRotate.setDuration((long) animRotate.getDuration());
        animRotate.setStartOffset(fade_in.getDuration());
        s.addAnimation(animRotate);

        fade_out.setStartOffset(fade_in.getDuration() + animRotate.getDuration());
        s.addAnimation(fade_out);

        s.setFillAfter(true);

        imageview.startAnimation(s);

        s.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                finish();
                Intent i = new Intent(getBaseContext(), FirstActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
