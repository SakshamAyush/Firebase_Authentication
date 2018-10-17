package com.example.saksham.firebase_authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    TextInputEditText email,pass;
    Button login,signup;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        //for see password option in UI, add design library in gradle (app)
        //Add the line app:passwordToggleEnabled="true" in inputedittext

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on clicking signup button finish the login activity
                finish();
                Intent i = new Intent(HomePage.this, SignUp.class);
                startActivity(i);
            }
        });

        //For login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if(mail.isEmpty())
                {
                    email.setError("E-mail is required");
                    email.requestFocus();
                    return;
                }
                else if(password.isEmpty())
                {
                    pass.setError("Password is required");
                    pass.requestFocus();
                    return;
                }
                    //To check if E-mail is entered as per the format
                else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                    Toast.makeText(HomePage.this ,"Enter a valid E-mail", Toast.LENGTH_SHORT).show();
                else
                {
                    mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //End this activity if user is successfully logged in
                                finish();
                                Intent i = new Intent(HomePage.this, Profile.class);
                                //Clear all activities and open a new activity
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(HomePage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}
