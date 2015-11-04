package com.matrix.neo.googlemapsintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    EditText getInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionsmenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OpenOptionsMenu(View view) {
        setContentView(R.layout.optionsmenu);
    }

    public void ShowImage(View view) {

        Intent showImage = new Intent(this, image_viewer.class);
        startActivity(showImage);

    }

    public void ShowSomethingElse(View view) {

        Intent showSomethingElse = new Intent(this, showSomething.class);
        startActivity(showSomethingElse);
    }

    public void process(View view)
    {

        Intent google_map = new Intent(android.content.Intent.ACTION_VIEW);


        getInput = (EditText) findViewById(R.id.AddressField);

        String address = getInput.getText().toString();


        google_map.setData(Uri.parse("geo:0,0?q=:" + address));

        startActivity(google_map);

    }
}
