package com.example.hidr8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView waterAmount;
    ProgressBar waterBar;
    ImageButton waterBottle;
    Button weeklyReport;
    double containerSize;
    double total;
    double incrementCount;
    double currentAmount = 0;
    String progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waterAmount = findViewById(R.id.waterAmount);
        waterBar = findViewById(R.id.progressBar);
        waterBottle = findViewById(R.id.WaterBottle);
        weeklyReport = findViewById(R.id.weeklyReport);

        containerSize = 8;
        total = 80;
        incrementCount = (containerSize/total) * 100;
        currentAmount = 0;

        progressText = (currentAmount + " fl oz / " + total + " fl oz");
        waterAmount.setText(progressText);

        weeklyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeeklyReport.class);
                intent.putExtra("randomVal", 50);
                startActivityForResult(intent, 1);
                onActivityResult(1, 2, intent);
            }
        });

    }

    public void waterOnClick(View view) {
        waterBar.incrementProgressBy((int)Math.round(incrementCount));
        currentAmount += (int)Math.round(containerSize);
        progressText = (currentAmount + " / " + total + " oz.");
        waterAmount.setText(progressText);
    }
}

