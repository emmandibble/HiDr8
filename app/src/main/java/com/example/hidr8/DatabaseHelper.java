package com.example.hidr8;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.sql.Time;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "water_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GOAL_PROGRESS = "goal_progress";
    private static final String TABLE_DAILY_INPUT = "daily_input";



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Error", "In onCreate()");
        db.execSQL("CREATE TABLE " + TABLE_GOAL_PROGRESS + "(" + " date DATE PRIMARY KEY," + " goal FLOAT," + " progress FLOAT)");
        db.execSQL("CREATE TABLE " + TABLE_DAILY_INPUT + "(" + " date DATE," + " time Time," + " amount FLOAT," + " PRIMARY KEY(date, time)," + " FOREIGN KEY(date) REFERENCES TABLE_GOAL_PROGRESS(date))");

        //used to test that the table is created properly
        ContentValues values = new ContentValues();
        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        values.put("date", date.toString());
        values.put("goal", 5.0);
        values.put("progress", 2.5);

        db.insert(TABLE_GOAL_PROGRESS, null, values);

        Time time = new Time(utilDate.getTime());


    }



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
