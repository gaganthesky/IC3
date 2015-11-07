package com.seven.gatorevents_connectionsqlite;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class dataActivity extends Activity {

    private Button btn;
    private TextView tv;
    openSqlite myDB = new openSqlite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        myDB.onUpgrade(myDB.getWritableDatabase(), 1, 2);



        myDB.createUser("Zhaohe");
        myDB.createUser("Vineet");

        tv = (TextView)findViewById(R.id.tvName);

        btn = (Button)findViewById(R.id.showData);
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                tv.setText(myDB.readData(1));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data, menu);
        return true;
    }



}
