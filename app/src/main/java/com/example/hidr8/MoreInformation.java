package com.example.hidr8;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MoreInformation extends AppCompatActivity {

    private String[] navigationDrawerItemTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence drawerTitle;
    private CharSequence title;
    ActionBarDrawerToggle drawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);

        title = drawerTitle = getTitle();
        navigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_more_information_array);
        drawerLayout = findViewById(R.id.drawer_more_information_layout);
        drawerList = findViewById(R.id.left_more_information_drawer);

        Toolbar toolbar = findViewById(R.id.information_toolbar);
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
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();


    }
}
