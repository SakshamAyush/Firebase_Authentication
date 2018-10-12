package com.example.saksham.firebase_authentication;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomePage extends AppCompatActivity {
    TextInputEditText email,pass;
    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        //for see password option in UI, add design library in gradle (app)
        //Add the line app:passwordToggleEnabled="true" in inputedittext

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, SignUp.class);
                startActivity(i);
            }
        });
    }
}
