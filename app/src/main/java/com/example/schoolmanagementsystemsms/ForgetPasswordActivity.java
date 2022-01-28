package com.example.schoolmanagementsystemsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    TextInputLayout Email_To_Change_Password;
    Button retrievePassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Email_To_Change_Password = findViewById(R.id.Email_Forgot_Password);
        retrievePassword = findViewById(R.id.Retrieve_Password);

        mAuth = FirebaseAuth.getInstance();

        retrievePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = Email_To_Change_Password.getEditText().getText().toString();

                if (Email.equals(""))
                {
                    Email_To_Change_Password.setError("Please Enter Email to Retrieve Your Password !");
                }
                else
                {
                    mAuth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Intent i = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                            startActivity(i);
                            Toast.makeText(ForgetPasswordActivity.this, "Password Reset Link send to your email.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ForgetPasswordActivity.this, "Error | Reset Link is not sent to your email: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

}