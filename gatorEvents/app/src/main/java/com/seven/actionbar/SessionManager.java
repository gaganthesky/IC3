package com.seven.actionbar;

/**
 * Created by vineet on 12/4/15.
 */

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    //Shared Preferences
    SharedPreferences pref;

    //Shared Preferences Editor
    Editor editor;

    //Context
    Context _context;

    //Shared pref mode
    int PRIVATE_MODE = 0;

    //SharedPref file name
    private static final String PREF_NAME = "GatorEventsPref";

    //SharedPref keys
    private static final String IS_LOGIN = "isLoggedIn";

    //User name
    public static final String KEY_NAME = "name";

    //User ID
    public static final String KEY_ID = "U_id";


    //Constructor
    public SessionManager(Context context)
    {
        this._context = context;
        pref =  context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /*
    Create Login Session
     */
    public void createLoginSession(String name, String id)
    {
        //Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        //Storing name in pref
        editor.putString(KEY_NAME, name);

        //Storing id in pref
        editor.putString(KEY_ID, id);

        //commit changes
        editor.commit();
    }

    /*
    Check login will check user's session status
     */

    public boolean checkLogin()
    {
        //Check login status
        if(!this.isLoggedIn())
        {
//            Intent i = new Intent(_context, LoginActivity.class);
//            //Closing all activities.
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            //Add new flag to start new activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            //Starting login activity
//            _context.startActivity(i);

            return false;
        }

        else
            return  true;
    }

    /*
    Get stored session data
     */
    public HashMap<String, String> getUserDetails()
    {
        HashMap<String, String> user = new HashMap<String, String>();

        //user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        //user id
        user.put(KEY_ID, pref.getString(KEY_ID, null));

        //return user
        return user;
    }

    /*
    Clear session details
     */
    public void logoutUser()
    {
        // Clearing all data from SharedPreferences
        editor.clear();
        editor.commit();

        //After logout redirect user to login activity
        Intent i = new Intent(_context, LoginActivity.class);

        //Closing all activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Add new flag to start new activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    /*
    Quick check for login
     */

    //Get login status
    public boolean isLoggedIn()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }


}
