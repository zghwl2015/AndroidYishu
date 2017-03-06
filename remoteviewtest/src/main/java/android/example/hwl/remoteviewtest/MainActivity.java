package android.example.hwl.remoteviewtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, )
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Hello World")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentText("this is context text")
                .setContentTitle("this title is later")
                .build();
        NotificationManager manager =(NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);



    }
}
