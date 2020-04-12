package com.example.hidr8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WeeklyReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report);

        ListView dateList = findViewById(R.id.date_data);
        ListView timeList = findViewById(R.id.time_data);
        ListView amountList = findViewById(R.id.amount_data);

        String arr[] = {"5", "2", "3", "4", "5", "1", "2"};

        DatabaseHelper db = new DatabaseHelper(this);


        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getDate());
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getTime());
        ArrayAdapter<String> amountAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getAmount());
        dateList.setAdapter(dateAdapter);
        timeList.setAdapter(timeAdapter);
        amountList.setAdapter(amountAdapter);
    }

    //on click method for the go back button
    public void goBackOnClick(View view) {
        Intent intent = new Intent(WeeklyReport.this, MainActivity.class);
        startActivity(intent);
    }
}
