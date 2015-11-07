package com.seven.actionbar;

/**
 * Created by vineet on 10/18/15.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity {

    EditText nameEditText, emailEditText;
    Button registerButton;

    public static final String myPreferences = "myPrefs";
    public static final String name = "nameKey";
    public static final String email = "emailKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

    }

    public void register(View view)
    {
        EditText nameEditText = (EditText) findViewById(R.id.nameText);
        EditText emailEditText = (EditText) findViewById(R.id.emailText);

        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);

        String nameString = nameEditText.getText().toString();
        String emailString = emailEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(name, nameString);
        editor.putString(email, emailString);
        editor.commit();

//        Intent intent = new Intent(this, RegisterSecond.class);
//        startActivity(intent);

    }

    public void login(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
