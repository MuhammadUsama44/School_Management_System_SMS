package com.example.schoolmanagementsystemsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieSchool;
    TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appName = findViewById(R.id.appname);
        lottieSchool = findViewById(R.id.lottie);

        //Applying animation on appName.
        appName.animate().translationY(+350).setDuration(4000).setStartDelay(0);
        //Applying animation to animation of lottie.
        lottieSchool.animate().translationX(2000).setDuration(3500).setStartDelay(7700);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
            }
        }, 5000);

    }
}