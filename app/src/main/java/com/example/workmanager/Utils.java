package com.example.workmanager;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

public class Utils {

    public static final int NOTIFICATION_ID = 22;
    private static final String CHANNEL_ID = "notify";
    private static final String CHANNEL_NAME = "workmanager-reminder";


    static void sendNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel1.enableVibration(true);
            channel1.enableLights(true);
            channel1.setLightColor(R.color.purple_200);
            channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            notificationManager.createNotificationChannel(channel1);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("WorkManager Sample")
                .setContentText("WorkManager Started")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground);

        notificationManager.notify(1, builder.build());
    }
}
