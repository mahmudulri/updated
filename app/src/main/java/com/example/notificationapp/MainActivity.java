package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int notificationId = 1; // Initialize the notification ID
    EditText notification_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationChannel channel = new NotificationChannel("1", "Notification", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        notification_text=findViewById(R.id.text);
    }

    public void notoficationButton(View view) {
        final String CHANNEL_ID = "1";
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        Intent activityIntent = new Intent(this, Video.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent activityCancelIntent = new Intent(this, MainActivity.class);
        PendingIntent cancelContentIntent = PendingIntent.getActivity(this, 0, activityCancelIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Notification"+notificationId)
                .setContentText(notification_text.getText().toString())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle("Notification "+notificationId)
                        .bigText(notification_text.getText().toString()))
                .setLargeIcon(largeIcon)
                .addAction(R.mipmap.ic_launcher, "Play", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Cancel", cancelContentIntent)
                .setColor(Color.RED)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        // Use a unique notification ID for each notification to create multiple notifications
        notificationManager.notify(notificationId, builder.build());

        // Increment the notification ID for the next notification
        notification_text.setText("");
        notificationId++;
    }
}
