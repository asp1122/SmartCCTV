package com.example.groupfour.smartcctv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by aswathip on 18/03/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Main.db";
    public static final String TABLE_NAME = "UserDB";
    public static final String COL_LOGIN = "LoginId";
    public static final String COL_NAME = "Name";
    public static final String COL_PASS = "Password";
    public static final String COL_PHONE = "Phone"; // to add later
    SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        //SQLiteDatabase db = this.getWritableDatabase(); //To test if working initially
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (LOGINID TEXT PRIMARY KEY, NAME TEXT, PASSWORD TEXT, PHONE TEXT )");
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(Contact contact)
    {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COL_LOGIN,contact.getLoginid());
        val.put(COL_NAME,contact.getName());
        val.put(COL_PASS,contact.getPassword());
        val.put(COL_PHONE,contact.getPhone());
        long result = sqLiteDatabase.insert(TABLE_NAME, null, val);

        if(result==-1)
            return false;
        return true;
    }

    public String searchPassword(String login)
    {
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select loginid, password from "+TABLE_NAME;
        String retPass = "not found";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String retLogin ;

        if(cursor.moveToFirst())
        {
            do{
                retLogin = cursor.getString(0);
                if(retLogin.equals(login))
                {
                    retPass = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }

        return retPass;
    }
}
