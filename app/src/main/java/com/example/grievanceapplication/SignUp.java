package com.example.grievanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    TextView btn;
    Button signup;
    EditText inputname;
    EditText inputemail;
    EditText inputpassword;
    EditText inputconfirmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn=findViewById(R.id.alreadyac);
        signup=findViewById(R.id.signup);
        inputname=findViewById(R.id.inputname);
        inputemail=findViewById(R.id.inputemail);
        inputpassword=findViewById(R.id.inputpassword);
        inputconfirmpass=findViewById(R.id.inputconfirmpass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,MakeGrievance.class));
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