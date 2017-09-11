package com.example.sammy.schoolsystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sammy.schoolsystem.utility.SessionManager;

import java.util.HashMap;

public class Menu extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> userdetails = sessionManager.getUserDetails();
        String  username = userdetails.get(SessionManager.KEY_USERNAME);

        new AlertDialog.Builder(this)
                .setMessage("THe username is "+username)
                .setTitle("Welcome")
                .show();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                sessionManager.logoutUser();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
