package com.example.sammy.schoolsystem.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sammy.schoolsystem.MainActivity;

import java.util.HashMap;

/**
 * Created by Sammy on 9/11/2017.
 */

public class SessionManager {

    // Shared Preferences reference
    private final SharedPreferences pref;

    // Editor reference for Shared preferences
    private final SharedPreferences.Editor editor;

    // Context
    private final  Context _context;

    // Shared pref mode
    private final int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "schooolsystem";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_USERNAME = "username";

//    public static final String KEY_HOSPITAL = "center";
//
//    public static final String KEY_POINTS = "points";
//
//    public static final String KEY_ROLES = "roles";
//
//    public static final String KEY_USERID = "userid";
//
//    public static final String KEY_SSID = "ssid";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    //Create login session
    public void createUserSession(String username) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        editor.putString(KEY_USERNAME, username);


//        editor.putString(KEY_HOSPITAL, centers);
//
//        editor.putString(KEY_POINTS, points);
//
//        editor.putString(KEY_ROLES, roles);
//
//        editor.putString(KEY_USERID, userid);

        // commit changes
        editor.apply();
    }



    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     */
    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<>();

        // user name
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));


//        user.put(KEY_HOSPITAL, pref.getString(KEY_HOSPITAL, null));
//
//
//        user.put(KEY_POINTS, pref.getString(KEY_POINTS, null));
//
//        user.put(KEY_ROLES, pref.getString(KEY_ROLES, null));
//
//
//        user.put(KEY_USERID, pref.getString(KEY_USERID, null));


        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences

        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, MainActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
