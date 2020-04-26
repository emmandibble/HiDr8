package com.example.hidr8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "water_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GOAL_PROGRESS = "goal_progress";
    private static final String TABLE_DAILY_INPUT = "daily_input";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GOAL_PROGRESS + "(" + " date DATE PRIMARY KEY," + " goal FLOAT," + " progress FLOAT)");
        db.execSQL("CREATE TABLE " + TABLE_DAILY_INPUT + "(" + " date DATE," + " time Time," + " amount FLOAT," + " PRIMARY KEY(date, time)," + " FOREIGN KEY(date) REFERENCES TABLE_GOAL_PROGRESS(date))");
    }

    /*method that inserts data into both tables when the user adds water to the goal
     *the values in the goal_progress table are overwritten if the user has already tracked water
     *for that day and new values are inserted into daily_input
     */
    public void insertData(Time time, Date date, float goal, float progress, float amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date.toString());
        values.put("goal", goal);
        values.put("progress", progress);
        db.insertWithOnConflict(TABLE_GOAL_PROGRESS, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        values.clear();
        values.put("date", date.toString());
        values.put("time", time.toString());
        values.put("amount", amount);
        db.insert(TABLE_DAILY_INPUT, null, values);
    }

    //method that returns an array list of all dates that have been recorded in the daily_input table
    //used to populate the date list view on the weekly report screen
    public ArrayList<String> getDate() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT date FROM daily_input ORDER BY date DESC LIMIT 20", null);
        ArrayList<String> array = new ArrayList<>(7);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    array.add(cursor.getString(cursor.getColumnIndex("date")));
                } while(cursor.moveToNext());
            }
        }

        return array;
    }

    //method that returns an array list of all times that have been recorded in the daily_input table
    //used to populate the time list view on the weekly report screen
    public ArrayList<String> getTime() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT time FROM daily_input ORDER BY date DESC LIMIT 20", null);
        ArrayList<String> array = new ArrayList<>(7);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    array.add(cursor.getString(cursor.getColumnIndex("time")));
                } while(cursor.moveToNext());
            }
        }

        return array;
    }

    //method that returns an array list of all amounts that have been recorded in the daily_input table
    //used to populate the amount list view on the weekly report screen
    public ArrayList<String> getAmount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT amount FROM daily_input ORDER BY date DESC LIMIT 20", null);
        ArrayList<String> array = new ArrayList<>(7);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    array.add(cursor.getString(cursor.getColumnIndex("amount")));
                } while(cursor.moveToNext());
            }
        }

        return array;
    }

    //method that returns an array that contains values representing during which day of the week the water goal was met
    public int[] getCompleteDays() {

        //create a new date and decrement the value by 7 to get a date below valid dates
        Date date = new Date(new java.util.Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -7);
        Date startDate = new Date(c.getTimeInMillis());

        SQLiteDatabase db = this.getReadableDatabase();
        //contains all records in goal_progress ordered by date in descending order
        Cursor cursor = db.rawQuery("Select * FROM goal_progress ORDER BY date DESC LIMIT 7", null);

        //creates a daysOfWeek array to hold -1 when the day is not complete and the value for the day of the week otherwise
        int[] daysOfWeek = {-1,-1,-1,-1,-1,-1,-1};
        //array counter that is incremented when a complete day is added to the array
        int arrayCounter = 0;


        //iterates over the database query and updates the daysOfWeek array
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do{
                    date = Date.valueOf(cursor.getString(cursor.getColumnIndex("date")));

                    if(startDate.compareTo(date) < 0) {
                        if(cursor.getFloat(cursor.getColumnIndex("progress")) >= cursor.getFloat(cursor.getColumnIndex("goal"))) {
                            c.setTime(date);
                            daysOfWeek[arrayCounter] = c.get(Calendar.DAY_OF_WEEK);
                            arrayCounter++;
                        }
                    }

                } while(cursor.moveToNext());
            }
        }

        return daysOfWeek;

    }


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
