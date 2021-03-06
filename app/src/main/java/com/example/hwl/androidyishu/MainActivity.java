package com.example.hwl.androidyishu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        context = getApplicationContext();

        Intent intent = new Intent(this, DemoActivity_1.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //remoteViews
        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                );

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Hello World")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentText("this is context text")
                .setContentTitle("this title is later")
                .setContentIntent(pendingIntent)
                .build();
        NotificationManager manager =(NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
}
