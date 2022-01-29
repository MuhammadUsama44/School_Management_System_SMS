package com.example.schoolmanagementsystemsms;

import android.app.ProgressDialog;

public class PROGRESS_DIALOGUE {

    private ProgressDialog progressDialog;

    // Method to show Progress bar
    public void showProgressDialogWithTitle(String title,String substring) {

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //Without this user can hide loader by tapping outside screen
        progressDialog.setCancelable(false);

        //Setting Title
        progressDialog.setTitle(title);
        progressDialog.setMessage(substring);
        progressDialog.show();

    }

    // Method to hide / dismiss Progress bar
    public void hideProgressDialogWithTitle() {

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }
}
