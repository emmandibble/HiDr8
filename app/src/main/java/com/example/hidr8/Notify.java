package com.example.hidr8;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Notify extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Notification builder = new Notification.Builder(context)
                .setSmallIcon(R.drawable.water_bottle)
                .setContentTitle("Drink Water!")
                .setContentText("Click to input water")
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder);
    }
}
