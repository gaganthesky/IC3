package com.seven.gatorevents_connectionsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class openSqlite extends SQLiteOpenHelper{

    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "test1.db";
    private static final String TABLE_NAME = "user";

    private static final String u_id = "id";
    private static final String u_name = "name";

    private static final String[] COLUMNS = {u_id, u_name};

    public openSqlite(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE = "CREATE TABLE user ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT)";
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        this.onCreate(db);
    }

    public void createUser(String s){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(u_name, s);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String readData(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + "" + TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        String x = new String();

        try{
            x = cursor.getString(1);
            cursor.moveToNext();
            return x;
        }
        catch(Exception e){
            throw e;
        }
    }
}
