package com.example.schoolmanagementsystemsms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class SignupActivity extends AppCompatActivity {

    TextInputLayout Email_SignUp, Password_SignUp, RepeatPassword_SignUp, FirstName_SignUp, LastName_SignUp, Address,
            Postal_Zip, Phone_Number;

    DatabaseReference reference;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Email_SignUp = findViewById(R.id.Email_SignUp);
        Password_SignUp = findViewById(R.id.Password_SignUp);
        RepeatPassword_SignUp = findViewById(R.id.Repeat_Password_SignUp);
        FirstName_SignUp = findViewById(R.id.FirstName_SignUp);
        LastName_SignUp = findViewById(R.id.LastName_SignUp);
        Address = findViewById(R.id.Address);
        Postal_Zip = findViewById(R.id.Postal_Zip);
        Phone_Number = findViewById(R.id.Phone_Number);

        reference = FirebaseDatabase.getInstance().getReference().child("School Users DB");

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

    }



    private Boolean validateEmail()
    {
        String Email = Email_SignUp.getEditText().getText().toString();
        String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if(Email.isEmpty())
        {
            Email_SignUp.setError("Field cannot be empty");
            return false;
        }
        else if( ! Email.matches(email_pattern))
        {
            Email_SignUp.setError("Invalid email address");
            return false;
        }
        else
        {
            Email_SignUp.setError(null); //to hide/remove error.
            Email_SignUp.setErrorEnabled(false); //To remove the space of the error.
            return true;
        }
    }

    private Boolean validatePassword()
    {
        String Pass = Password_SignUp.getEditText().getText().toString();
        String password_pattern = "^" +
                "(?=.*[0-9])" +           // at least 1 digit
                "(?=.*[a-z])" +           //at least 1 lower case
                "(?=.*[A-Z])" +           //at least 1 upper case
                "(?=.*[@#$%^&+=])" +     // at least 1 special character
                "(?=\\S+$)" +            // no white spaces
                ".{6,}" +                // at least 6 characters
                "$";

        if (Pass.isEmpty())
        {
            Password_SignUp.setError("Field cannot be empty");
            return false;
        }
//        else if( ! password_pattern.matches(Pass))
//        {
//            Password_SignUp.setError("Password is too weak");
//            return false;
//        }
        else
        {
            Password_SignUp.setError(null);
            Password_SignUp.setErrorEnabled(false);//remove the space occupied by error.
            return true;
        }
    }

    private Boolean validate_RepeatPassword()
    {
        String Pass = Password_SignUp.getEditText().getText().toString();
        String Repeat_Pass = RepeatPassword_SignUp.getEditText().getText().toString();

        if(Repeat_Pass.isEmpty())
        {
            RepeatPassword_SignUp.setError("Field cannot be empty");
            return false;
        }
        else if( ! Repeat_Pass.equals(Pass)) // else if(Repeat_Pass != Pass);  do not working.
        {
            RepeatPassword_SignUp.setError("Password do not match");
            return false;
        }
        else
        {
            RepeatPassword_SignUp.setError(null);
            RepeatPassword_SignUp.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validate_FirstName()
    {
        String FirstName = FirstName_SignUp.getEditText().getText().toString();

        if(FirstName.isEmpty())
        {
            FirstName_SignUp.setError("Field cannot be empty");
            return false;
        }
        else if(FirstName.length() < 3)
        {
            FirstName_SignUp.setError("First Name is too short");
            return false;
        }
        else if(FirstName.length() >= 15)
        {
            FirstName_SignUp.setError("First Name is too long");
            return false;
        }
        else
        {
            FirstName_SignUp.setError(null);
            FirstName_SignUp.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validate_LastName() {
        String LastName = LastName_SignUp.getEditText().getText().toString();

        if (LastName.isEmpty()) {
            LastName_SignUp.setError("Field cannot be empty");
            return false;
        } else if (LastName.length() < 3) {
            LastName_SignUp.setError("Last Name is too short");
            return false;
        } else if (LastName.length() >= 15) {
            LastName_SignUp.setError("Last Name is too long");
            return false;
        } else {
            LastName_SignUp.setError(null);
            LastName_SignUp.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateAddress()
    {
        String address = Address.getEditText().getText().toString();

        if(address.isEmpty())
        {
            Address.setError("Field cannot be empty");
            return false;
        }
        else if(address.length() >= 25)
        {
            Address.setError("Address is too long");
            return false;
        }
        else {
            Address.setError(null);
            Address.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validate_Postal_Zip_Code()
    {
        String zip_postal = Postal_Zip.getEditText().getText().toString();

        if(zip_postal.isEmpty())
        {
            Postal_Zip.setError("Field cannot be empty");
            return false;
        }
        else if(zip_postal.length() > 10)
        {
            Postal_Zip.setError("Postal or Zip code is too long");
            return false;
        }
        else {
            Postal_Zip.setError(null);
            Postal_Zip.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validate_PhoneNum()
    {
        String phone = Phone_Number.getEditText().getText().toString();

        if(phone.isEmpty())
        {
            Phone_Number.setError("Field cannot be empty");
            return false;
        }
        else if(phone.length() > 15)
        {
            Phone_Number.setError("Phone number is too long");
            return false;
        }
        else {
            Phone_Number.setError(null);
            Phone_Number.setErrorEnabled(false);
            return true;
        }
    }



    // Create Account Button Functioning.

    public void Create_Account(View view) {

        if(!validateEmail() | !validatePassword() | !validate_RepeatPassword() | !validate_FirstName()
                | !validate_LastName() | !validateAddress() | !validate_Postal_Zip_Code() | !validate_PhoneNum())
        {
            return;

            // || means either the first one or the second 1 and in any case our condition will be true and our
            // if will be executed. So we don't want to be executed this at any one of these true. Because we
            // want to validate all of these functions.

            // Inside this if we will simply return. If any of them is not valid, so the when compiler reaches
            // there and executes all functions and if the compiler finds any one of thee functions returning
            // false. It will simply returns from this function which is Create_Account().
            // Otherwise compiler perform the intent code.
        }

        String email = Email_SignUp.getEditText().getText().toString();
        String pass = RepeatPassword_SignUp.getEditText().getText().toString(); //Repeat Password Edit Text.

        //To Generate Email Link Sign-In / Verification For User AND To Save UserID in Firebase.

//        String url = "http://www.example.com/verify?uid=" + Uid;
//
//        ActionCodeSettings actionCodeSettings =
//                ActionCodeSettings.newBuilder()
//                        .setUrl(url)
//                        // This must be true
//                        .setHandleCodeInApp(true)
//                        .setAndroidPackageName(
//                                "com.example.finalproject",
//                                true, /* installIfNotAvailable */
//                                "12"    /* minimumVersion */)
//                        .build();
//
//        currentUser.sendEmailVerification(actionCodeSettings)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful())
//                        {
//                            Toast.makeText(SignUp_Activity3.this, "Email Sent", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


        mAuth.createUserWithEmailAndPassword(email, pass).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                        showProgressDialogWithTitle("Signing-Up", "Please Wait..!");

                        if(task.isSuccessful())
                        {
                            Intent i = new Intent(SignupActivity.this, MainActivity.class);

                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            //This flag is ok but it makes the screen popping/dimming like. so we need to use simple finish() and
                            //make a function for back button of mobile/noxplayer to go back to the screen login.

                            // i.putExtra("EXIT", true);??
                            startActivity(i);
                            finish();

                            //finishAffinity();??

                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String id = currentUser.getUid();
                            saveData_In_Firebase(id); //Function Calling. To save ID of User and Personal Data.

                            hideProgressDialogWithTitle();

                            Toast.makeText(SignupActivity.this, "Welcome To School Management System", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            hideProgressDialogWithTitle();

                            Toast.makeText(SignupActivity.this, "Authentication Failed !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void Login_Here(View view) {

        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void saveData_In_Firebase(String ID) {

        String uid = ID;

        String email      = Email_SignUp.getEditText().getText().toString();
        String pass       = RepeatPassword_SignUp.getEditText().getText().toString();
        String f_name     = FirstName_SignUp.getEditText().getText().toString();
        String l_name     = LastName_SignUp.getEditText().getText().toString();
        String address    = Address.getEditText().getText().toString();
        String zip_postal = Postal_Zip.getEditText().getText().toString();
        String phone      = Phone_Number.getEditText().getText().toString();

        School_Modal SM = new School_Modal(uid, email, pass, f_name, l_name, address, zip_postal, phone);

        reference.child(uid).setValue(SM, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NotNull DatabaseReference ref) {

                if (error != null)
                {
                    Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(SignUp_Activity3.this, "Data of New User Saved Successfully !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // Method to show Progress bar
    private void showProgressDialogWithTitle(String title,String substring) {

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //Without this user can hide loader by tapping outside screen
        progressDialog.setCancelable(false);

        //Setting Title
        progressDialog.setTitle(title);
        progressDialog.setMessage(substring);
        progressDialog.show();

    }

    // Method to hide / dismiss Progress bar
    private void hideProgressDialogWithTitle() {

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }

}