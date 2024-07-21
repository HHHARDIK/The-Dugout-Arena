package com.example.thedougoutarena;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    int bid=100;
    public static final String DBNAME = "Dugout.db";

    public DBHelper(Context context) {
        super(context, "Dugout.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("Create Table ground1(bid integer primary key, name TEXT, phone TEXT, time TEXT, date TEXT)");
        MyDB.execSQL("Create Table ground2(bid integer primary key,name TEXT, phone TEXT, time TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("Drop table if exists users");
        MyDB.execSQL("Drop table if exists ground1");
        MyDB.execSQL("Drop table if exists ground2");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyBD = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyBD.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() >0) return true;
        else return false;
    }

    public Boolean checkPassword(String username,String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount() >0) return true;
        else return false;
    }

    public Boolean checkGround(String date,String time, String ground) {
        String g;
        if(ground=="1"){
            g="ground1";
        }
        else{
            g="ground2";
        }
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from "+g+"  where time = "+time+" and date = "+date,null);
        if (cursor.getCount() >0) return true;
        else return false;
    }

    public Boolean bookGround(String name, String phone, String time, String date, String ground ){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = MyDB.rawQuery("select * from ground1 ", null);
        bid=101+ cursor.getCount();
        contentValues.put("bid", bid);
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("time", time);
        contentValues.put("date", date);

        long result = MyDB.insert("ground1", null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }
}