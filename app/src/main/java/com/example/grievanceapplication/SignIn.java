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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

import java.util.Objects;

public class SignIn extends AppCompatActivity {
    TextView btn1;
    Button signin;
    EditText inputname,inputpassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn1=findViewById(R.id.textviewsignup);
        signin=findViewById(R.id.signin);
        inputname=findViewById(R.id.inputname);
        inputpassword=findViewById(R.id.inputpassword);
        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(SignIn.this,MakeGrievance.class));
            finish();
        }
        else {
            startActivity(new Intent(SignIn.this,SignUp.class));
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this,SignUp.class));
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputname.getText().toString().trim();
                String password=inputpassword.getText().toString().trim();

                if((email.toString()).equals("")){
                    inputname.setError("Email is Required!");
                    return;
                }
                if((password.toString()).equals("")){
                    inputname.setError("Password is Required!");
                    return;
                }
                if((password.length())<6){
                    inputpassword.setError("Password must be equal to or greater than 6 characters!");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                //authenticate user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SignIn.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignIn.this,MakeGrievance.class));


                        }
                        else{
                            Toast.makeText(SignIn.this, "Error! "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(SignIn.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                                inputpassword.setText("Invalid Password!");
                            }
                            else {
                                if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                    Toast.makeText(SignIn.this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                                    inputname.setText("Invalid Email!");
                                }
                            }
                        }

                    }
                });
            }
        });
    }
}