package com.example.hidr8;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //variable used to hold the text field that displays the water amount
    TextView waterAmountText;
    //variable used to connect to the progress bar
    ProgressBar waterProgressBar;
    //variable used to connect to the water bottle image that is used as a button
    ImageButton waterBottle;
    //variable used to connect to the weekly report button
    Button weeklyReportButton;
    //variable used to hold the current containerSize
    double containerSize;
    //variable used to hold the goal amount
    double goal;
    //variable used to define the amount that the progress bar should advance based on the container size
    double incrementCount;
    //variable used to hold the currentAmount of water that has been drank for the day
    double currentAmount = 0;

    private String[] navigationDrawerItemTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence drawerTitle;
    private CharSequence title;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configureToolbar();
        title = drawerTitle = getTitle();
        navigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, navigationDrawerItemTitles);
        drawerList.setAdapter(adapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //when first item is selected
                        break;
                    case 1:
                        //when second item is selected
                        break;
                }


            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();

        waterAmountText = findViewById(R.id.waterAmount);
        waterProgressBar = findViewById(R.id.progressBar);
        waterBottle = findViewById(R.id.WaterBottle);
        weeklyReportButton = findViewById(R.id.weeklyReport);




        //defines default values
        containerSize = 8;
        goal = 80;
        incrementCount = (containerSize/goal) * 100;
        currentAmount = 0;

        //sets the text above the waterProgressBar to the currentAmount next to the goal value
        waterAmountText.setText(currentAmount + " fl oz / " + goal + " fl oz");

        //creates a OnClickListener for the weeklyReportButton that starts the WeeklyReport activity
        //when the button is clicked
        weeklyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeeklyReport.class);
                startActivity(intent);
            }
        });

    }


    public void waterOnClick(View view) {
        //increments the waterProgressBar by the amount determined by the container size
        waterProgressBar.incrementProgressBy((int)Math.round(incrementCount));
        //increases the currentAmount based on the current containerSize
        currentAmount += (int)Math.round(containerSize);
        waterAmountText.setText(currentAmount + " fl oz / " + goal + " fl oz");
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        //actionbar.setHomeAsUpIndicator(R.drawable.ic_action_menu_white);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }
}

