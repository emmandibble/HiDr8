package com.example.hidr8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WeeklyReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report);
    }

    public void goBackOnClick(View view) {
        Intent intent = new Intent(WeeklyReport.this, MainActivity.class);
        startActivity(intent);
    }
}
