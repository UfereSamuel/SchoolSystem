package com.example.sammy.schoolsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sammy.schoolsystem.utility.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    CheckBox checkBox;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        sessionManager = new SessionManager(getApplicationContext());


        HashMap<String, String> userdetails = new HashMap<>();

        userdetails = sessionManager.getUserDetails();
        String  username = userdetails.get(SessionManager.KEY_USERNAME);

        if (username != null) {
           startActivity(new Intent(MainActivity.this, Menu.class));
            finish();
        } else {

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });

            //this session takes care of the checkbox actions

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }

    }


    public boolean login(){

        String email = etEmail.getText().toString();
        if(email.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Enter Email Address")
                    .show();
            return false;
        }

        if(checkBox.isChecked()){
           sessionManager.createUserSession(email);
        }

        startActivity(new Intent(MainActivity.this, Menu.class));

        return true;
    }
}
