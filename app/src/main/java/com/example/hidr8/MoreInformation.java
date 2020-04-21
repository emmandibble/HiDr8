package com.example.hidr8;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MoreInformation extends AppCompatActivity {

    //variable used to store the titles of button in the navigation drawer
    private String[] navigationDrawerItemTitles;
    //variable that connects to the DrawerLayout in activity_more_information.xml
    private DrawerLayout drawerLayout;
    //ListView that holds the items that are in the navigation drawer
    private ListView drawerList;
    //holds the ActionBarDrawerToggle that is shown as a hamburger button
    ActionBarDrawerToggle drawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);

        //this flips all of the layout elements and is a quick and dirty solution to open the navigation drawer from right to left
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        navigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_more_information_array);
        drawerLayout = findViewById(R.id.drawer_more_information_layout);
        drawerList = findViewById(R.id.left_more_information_drawer);

        Toolbar toolbar = findViewById(R.id.information_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //creates an array adapter that holds the values of the item titles for the list that is displayed in the navigation drawer
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, navigationDrawerItemTitles);
        drawerList.setAdapter(adapter);

        //listener that starts activities depending on the item in the list view that is clicked
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //when first item is selected
                        startActivity(new Intent(MoreInformation.this, Settings.class));
                        break;
                    case 1:
                        //when second item is selected
                        startActivity(new Intent(MoreInformation.this, MainActivity.class));
                        break;
                }


            }
        });

        drawerLayout = findViewById(R.id.drawer_more_information_layout);
        drawerLayout.addDrawerListener(drawerToggle);
        //sets drawerToggle to a new ActionBarDrawerToggle which is a hamburger button that opens the navigation drawer
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
    }

    //OnClick method for the learn more button
    public void learnMoreOnClick(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/healthywater/drinking/nutrition/index.html")));
    }



}
