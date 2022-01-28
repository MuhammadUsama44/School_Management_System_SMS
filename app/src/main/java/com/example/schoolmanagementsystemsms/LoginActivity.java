package com.example.schoolmanagementsystemsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

        TextInputLayout Email_Login, Password_Login;

        DatabaseReference reference;

        FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


            Email_Login = findViewById(R.id.Email_Login);
            Password_Login = findViewById(R.id.Password_Login);

            reference = FirebaseDatabase.getInstance().getReference().child("School Users DB");

            mAuth = FirebaseAuth.getInstance();

            //If User Already Signed In, then this code works.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null)
            {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

        }

        public void Login_Btn(View view) {

            String email = Email_Login.getEditText().getText().toString();
            String pass = Password_Login.getEditText().getText().toString();

            if(email.equals(""))
            {
                Email_Login.setError("Empty Field");
            }
            else if(pass.equals(""))
            {
                Password_Login.setError("Empty Field");
            }
            else {

                mAuth.signInWithEmailAndPassword(email, pass).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                if (task.isSuccessful())
                                {
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i);
                                    finish();

                                    Toast.makeText(LoginActivity.this, "Welcome To School Management System", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Login Failed, Email or Password is incorrect.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }

        public void Forget_Pass_Btn(View view) {

            Intent i = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(i);
        }

        public void Register_Now(View view) {

            Intent i = new Intent(LoginActivity.this, SignupActivity. class);
            startActivity(i);
            finish();
        }

        public void Continue_As_Guest(View view) {

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

}