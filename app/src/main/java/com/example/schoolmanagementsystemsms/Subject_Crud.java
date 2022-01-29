package com.example.schoolmanagementsystemsms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Subject_Crud extends AppCompatActivity {

    Button add_course, update_course, view_course;
    ListView Courses_LIST;
    EditText NAME, ID;

    DatabaseReference reference;
    FirebaseAuth mAuth;

    TextView Error_Text;

    List<School_Modal> SCHOOLArray = new ArrayList<>();

    PROGRESS_DIALOGUE progress_dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_crud);



            add_course = findViewById(R.id.add_btn);
            view_course = findViewById(R.id.view_btn);
            update_course = findViewById(R.id.update_btn);

            NAME = findViewById(R.id.courseName);
            ID = findViewById(R.id.courseID);

            Error_Text = findViewById(R.id.error);
            Courses_LIST = findViewById(R.id.Courses_LIST);


            FirebaseApp.initializeApp(this);
            reference = FirebaseDatabase.getInstance().getReference().child("COURSES");

            progress_dialogue = new PROGRESS_DIALOGUE();


            add_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = NAME.getText().toString();
                    String id = ID.getText().toString();

                    //Getting id by pushing a new child(user) into USERS Node.
                    String IDD = reference.push().getKey();

                    School_Modal SM = new School_Modal(name, id);

                    progress_dialogue.showProgressDialogWithTitle("Saving Data", "Please Wait...");

                    assert IDD != null;
                    reference.child(IDD).setValue(SM, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, DatabaseReference ref) {

                            //To check task is successful or not.
                            if (error == null) {
                                progress_dialogue.hideProgressDialogWithTitle();
                                Toast.makeText(Subject_Crud.this, "Data Saved Successfully.", Toast.LENGTH_SHORT).show();
                            } else {
                                progress_dialogue.hideProgressDialogWithTitle();
                                Toast.makeText(Subject_Crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    NAME.setText("");
                    ID.setText("");


                }
            });

            view_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot snap : snapshot.getChildren())
                            {
                                School_Modal SM = snap.getValue(School_Modal.class);
                                SCHOOLArray.add(SM);
                            }

                            DisplayStudents(SCHOOLArray);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            Error_Text.setText(error.getMessage());

                        }
                    });

                }
            });


            Courses_LIST.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    String MyId = SCHOOLArray.get(position).getID();

                    @SuppressLint("ResourceType") MaterialAlertDialogBuilder material_Dialog = new MaterialAlertDialogBuilder(Student_Crud.this)
                            .setTitle("Delete?")
                            .setMessage("Are you sure want to delete this user?")
                            .setCancelable(false)
                            .setIcon(R.raw.delete_animation)
                            .setPositiveButton("Delete", R.drawable.ic_baseline_delete_24, new MaterialDialogs.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {

                                    progress_dialogue.showProgressDialogWithTitle("Deleting Data", "Please Wait...");

                                    reference.child(MyId).removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, DatabaseReference ref) {

                                            if(error == null)
                                            {
                                                //To Remove Data From ListView as well.
                                                SCHOOLArray.remove(position);

                                                //To display new list after removing data from list as well.
                                                ListAdapter usersAdapter = new ListAdapter(Subject_Crud.this, SCHOOLArray);
                                                Courses_LIST.setAdapter(usersAdapter);

                                                progress_dialogue.hideProgressDialogWithTitle();

                                                Toast.makeText(Subject_Crud.this, "Data Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                progress_dialogue.hideProgressDialogWithTitle();

                                                Toast.makeText(Subject_Crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButtonIcon(R.drawable.ic_baseline_close_24)
                            .build();

                    material_Dialog.show();

                    return false;
                }
            });

            update_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = NAME.getText().toString();
                    String id = ID.getText().toString();


                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    String ID = currentUser.getUid();

                    School_Modal SM = new School_Modal(name, id);
                    reference.child(ID).setValue(SM, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                            if (error != null)
                            {
                                Toast.makeText(Subject_Crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                //Toast.makeText(SignUp_Activity3.this, "Data of New User Saved Successfully !", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            });

        }


        private void DisplayStudents(List<School_Modal> data) {

            ListCourseAdapter LA = new ListCourseAdapter(Subject_Crud.this, data);
            Courses_LIST.setAdapter(LA);

        }
    }