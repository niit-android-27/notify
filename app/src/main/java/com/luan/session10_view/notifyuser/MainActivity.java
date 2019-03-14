package com.luan.session10_view.notifyuser;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    NotificationCompat.Builder notificationCompatBuilder;
    Notification notificationCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            //android less than api 27 (Oreo)
            notificationCompatBuilder = new NotificationCompat.Builder(getApplicationContext());
        }else {
            //android greater than api 27
            //create channel id
            notificationCompatBuilder = new NotificationCompat.Builder(getApplicationContext());
            String CHANNEL_ID = "my_nof_channel_id";
            String CHANNEL_NAME = "my_nof_channel_name";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(false);
            notificationChannel.setSound(null,null);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationCompatBuilder.setChannelId(CHANNEL_ID);
        }

        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_view);

        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(getApplicationContext(),0,intent1,0);
        remoteViews.setOnClickPendingIntent(R.id.btnOpenAct1,pendingIntent1);
        notificationCompatBuilder
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                //.setContentText("Hi")
                //.setContentTitle("My App").addAction(R.drawable.ic_add_alert_black_24dp,"Open",pendingIntent)
                //.setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews);
                //.addAction(R.drawable.ic_add_alert_black_24dp,"Close",pendingIntent).
                //addAction(R.drawable.ic_add_alert_black_24dp,"Delete",pendingIntent);

         notificationCompat= notificationCompatBuilder.build();

         notificationManager.notify(10,notificationCompat);

    }


}
