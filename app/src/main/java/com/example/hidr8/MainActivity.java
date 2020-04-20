package com.example.hidr8;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity{

    //variable used to hold the text field that displays the water amount
    TextView waterAmountText;
    //variable used to connect to the progress bar
    ProgressBar waterProgressBar;
    //variable used to connect to the water bottle image that is used as a button
    ImageButton waterBottle;
    //variable used to connect to the weekly report button
    Button weeklyReportButton;
    //variable used to hold the current containerSize
    float containerSize;
    //variable used to hold the goal amount
    float goal;
    //variable used to define the amount that the progress bar should advance based on the container size
    float incrementCount;
    //variable used to hold the currentAmount of water that has been drank for the day
    float currentAmount;

    //string array that contains the titles of buttons in the navigation drawer
    private String[] navigationDrawerItemTitles;
    //variable that connects to the DrawerLayout in the xml file
    private DrawerLayout drawerLayout;
    //ListView that is contained inside of the navigation drawer
    private ListView drawerList;
    //variable that holds the hamburger button ActionBarDrawerToggle
    ActionBarDrawerToggle drawerToggle;

    SharedPreferences pref;

    TextView recommendedGoal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //stores the default shared preferences into pref
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //this flips all of the layout elements and is a quick and dirty solution to open the navigation drawer from right to left
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        navigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_main_array);

        drawerLayout = findViewById(R.id.drawer_main_layout);
        drawerList = findViewById(R.id.left_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //removes the title of the app from the toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //this code can be used to change the color of a drawable for the update to the circles that represent
        //the days of the week a separate drawable will need to be updated for each day of the week
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(this, R.drawable.circle);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, Color.RED);

        recommendedGoal = findViewById(R.id.recommended_goal);
        new GetWebServiceData().execute();





        //array adapter created for the titles in the navigation drawer
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, navigationDrawerItemTitles);
        drawerList.setAdapter(adapter);

        //listener that switches activities when the user presses a button in the navigation drawer
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //when first item is selected
                        startActivity(new Intent(MainActivity.this, Settings.class));
                        break;
                    case 1:
                        //when second item is selected
                        startActivity(new Intent(MainActivity.this, MoreInformation.class));
                        break;
                }
            }
        });

        //set drawerLayout to the DrawerLayout that is the top level layout in activity_main.xml
        drawerLayout = findViewById(R.id.drawer_main_layout);
        //add a drawer listener that allows the navigation drawer to open when the hamburger button is pressed
        drawerLayout.addDrawerListener(drawerToggle);
        //create a new ActionBarDrawerToggle that is the hamburger button that will be pressed
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();

        waterAmountText = findViewById(R.id.waterAmount);
        waterProgressBar = findViewById(R.id.progressBar);
        waterBottle = findViewById(R.id.WaterBottle);
        weeklyReportButton = findViewById(R.id.weeklyReport);




        //defines values based on the default shared preferences
        containerSize = pref.getFloat("container_size", 8);
        goal = Float.parseFloat(pref.getString("goal", "80"));
        incrementCount = (containerSize/goal) * 100;
        currentAmount = pref.getFloat("current_amount", 0);

        waterProgressBar.setProgress((int)((currentAmount / goal) * 100));

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
        waterProgressBar.incrementProgressBy(Math.round(incrementCount));
        //increases the currentAmount based on the current containerSize
        currentAmount += Math.round(containerSize);
        waterAmountText.setText(currentAmount + " fl oz / " + goal + " fl oz");
        SharedPreferences.Editor edit = pref.edit();
        edit.putFloat("current_amount", currentAmount);
        edit.apply();

        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());
        Time time = new Time(utilDate.getTime());
        DatabaseHelper db = new DatabaseHelper(this);
        db.insertData(time, date, goal, currentAmount, containerSize);
    }


    public class GetWebServiceData extends AsyncTask {


        @Override
        protected Object doInBackground(Object[] objects) {
            StringBuffer response;
            URL url;
            String responseText;
            String returnedGoal = "";

            try {
                int testWeight = 50;
                url = new URL("http://70.32.24.170:8080/goal?weight=" + testWeight);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();

                Log.e("WebService", "Response code: " + responseCode);
                response = new StringBuffer();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String output;
                    response = new StringBuffer();

                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();
                }


                responseText = response.toString();
                Log.i("WebService", responseText);

                returnedGoal = "";


                JSONObject jsonResponse = new JSONObject(responseText);
                if (jsonResponse.has("goal")) {
                    returnedGoal = jsonResponse.getString("goal");
                }
                Log.e("Test goal", "The returned goal is: " + returnedGoal);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedGoal;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            recommendedGoal.setText("Recommended goal: " + (String)o + " fl oz");
        }
    }
}

