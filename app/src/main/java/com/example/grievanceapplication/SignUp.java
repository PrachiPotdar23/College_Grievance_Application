package com.example.grievanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    TextView btn;
    Button signup;
    EditText inputname,inputemail,inputpassword;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn=findViewById(R.id.alreadyac);
        signup=findViewById(R.id.signup);
        inputname=findViewById(R.id.inputname);
        inputemail=findViewById(R.id.inputemail);
        inputpassword=findViewById(R.id.inputpassword);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(SignUp.this,SignIn.class));
            finish();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputemail.getText().toString().trim();
                String password=inputpassword.getText().toString().trim();

                if((email.toString()).equals("")){
                    inputemail.setError("Email is Required!");
                    return;
                }
                if((password.toString()).equals("")){
                    inputemail.setError("Password is Required!");
                    return;
                }
                if((password.length())<6){
                    inputpassword.setError("Password must be equal to or greater than 6 characters!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //register user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User is Created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this,MakeGrievance.class));
                        }
                        else {
                            Toast.makeText(SignUp.this, "Error! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,SignIn.class));
            }
        });
    }
}