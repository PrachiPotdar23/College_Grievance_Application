package com.example.grievanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class MakeGrievance extends AppCompatActivity {

    Spinner spinner;
    TextView make_grievance;
    EditText name,title,description;
    Button submit;

    DatabaseReference studentDbref;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_grievance);

        make_grievance=findViewById(R.id.make_grievance);
        name=findViewById(R.id.name);
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        submit=findViewById(R.id.submit);

        studentDbref= FirebaseDatabase.getInstance().getReference().child("Students");


        spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Branch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.toString().equals("")){
                    name.setError("Name is required!");
                }
//                SignUp signUp=new SignUp();
//                if(!(name.toString().equals(signUp.inputemail.toString()))){
//                    name.setError("Email invalid!");
//                };
                if(title.toString().equals("")){
                    title.setError("Title is required!");
                }
                if(description.toString().equals("")){
                    description.setError("Description is required!");
                }
                if((name.toString()!=null) && (title.toString()!=null) && (description.toString()!=null)){
                    insertStudentData();
                    Toast.makeText(MakeGrievance.this, "Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertStudentData(){
        String Name= name.getText().toString();
//        if(Name.toString().equals("")){
//            name.setError("Name is required!");
//        }
//        SignUp signUp=new SignUp();
//        if(!(Name.toString().equals(signUp.inputemail))){
//            name.setError("Email invalid!");
//        };
        String Title=title.getText().toString();
//        if(Title.toString().equals("")){
//            title.setError("Title is required!");
//        }
        String Description=description.getText().toString();
//        if(Description.toString().equals("")){
//            description.setError("Description is required!");
//        }
        String Branch=spinner.getSelectedItem().toString();

        Students students=new Students(Name,Title,Description,Branch);
        db=FirebaseDatabase.getInstance();
        studentDbref=db.getReference("Students");
        studentDbref.child(Name).push().setValue(students);
//


    }
}