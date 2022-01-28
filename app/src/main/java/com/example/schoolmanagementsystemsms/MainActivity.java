 package com.example.schoolmanagementsystemsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

 public class MainActivity extends AppCompatActivity {

     Button Logout;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logout = findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentUser != null)
                {
                    mAuth.signOut();
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(MainActivity.this, "You are Successfully Logged Out !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "You are not able to logged out, you are a guest !", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}