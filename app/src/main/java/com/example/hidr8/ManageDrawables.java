package com.example.hidr8;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

public class ManageDrawables {

    //method that sets the day of the week circles based on which day of the week the water goal was met
    public static void setDayOfWeekColors(int[] days, Context context) {
        //loop that iterates over the passed in days array and sets the color based on the value in
        //the array at each position in the array
        for(int i = 0; i < days.length; i++) {
            //only check the cases if the days[i] does not contain the flag value of -1
            if(days[i] != -1) {
                //the value that is in days[i] determines which drawable is set to the color blue
                switch(days[i]) {
                    case 1:
                        //gets the drawable and sets the color to blue
                        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.sunday);
                        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
                        break;
                    case 2:
                        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.monday);
                        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
                        break;
                    case 3:
                        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.tuesday);
                        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
                        break;
                    case 4:
                        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.wednesday);
                        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
                        break;
                    case 5:
                        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.thursday);
                        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
                        break;
                    case 6:
                        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.friday);
                        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
                        break;
                    case 7:
                        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.saturday);
                        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.BLUE);
                        break;
                }
            }
        }
    }

    //resets the drawables to gray
    public static void setDefaultDayOfWeekColors(Context context, int color) {
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.sunday);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.monday);
        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.tuesday);
        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.wednesday);
        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.thursday);
        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.friday);
        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.saturday);
        wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, color);
    }
}
