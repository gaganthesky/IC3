package com.gatorevents.www.sqlitecrudexample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SQLiteCrudExample extends ListActivity {


    // DB Name
    private final String dbName = "Android";

    // Table Name
    private final String tableName = "Versions";

    // String array has version names that will be populated in the list
    private final String[] versionNames = new String[]
            {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                    "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "Kitkat",
                            "Lollipop", "Marshmallow"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);

        ArrayList<String> results = new ArrayList<String>();

        //Declare SQLiteDatabase object
        SQLiteDatabase sampleDB = null;

        try
        {
            //Instantiate Sample DB Object
            sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            //Create table using execSQL
            sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (versionName VARCHAR);");

            //Insert Android versions into table created
            for(String ver: versionNames)
            {
                sampleDB.execSQL("INSERT INTO " + tableName + " Values ('" + ver + "');");
            }

            //Create Cursor object to read versions from the table
            Cursor c = sampleDB.rawQuery("SELECT versionName FROM " + tableName, null);

            //if Cursor is valid
            if(c!=null)
            {
                //Move Cursor to first row
                if (c.moveToFirst())
                {
                    do
                    {
                        // Get version name from Cursor
                        String firstName = c.getString(c.getColumnIndex("versionName"));
                        // Add the name to the list
                        results.add(firstName);
                    }
                    while(c.moveToNext());
                }
            }
            this.setListAdapter(
                    new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            results));

        }
        catch(SQLiteException se)
        {
            Toast.makeText(getApplicationContext(), "Couldn't create or open the database",
                    Toast.LENGTH_LONG).show();
        }

        finally
        {
            if (sampleDB != null)
            {
                sampleDB.execSQL("DELETE FROM " + tableName);
                sampleDB.close();
            }
        }
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
}
