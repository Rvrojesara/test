//3. Build an app that sends a notification when the user completes a task.
// Add multiple actions to the notification (e.g., "Mark as Done," "Snooze").


package com.example.practice_3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "task_channel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.notifyButton).setOnClickListener(v -> showNotification());
    }

    private void showNotification() {
        createNotificationChannel();

        Intent doneIntent = new Intent(this, NotificationActionReceiver.class);
        doneIntent.setAction("ACTION_MARK_DONE");
        PendingIntent donePendingIntent = PendingIntent.getBroadcast(this, 0, doneIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent snoozeIntent = new Intent(this, NotificationActionReceiver.class);
        snoozeIntent.setAction("ACTION_SNOOZE");
        PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(this, 1, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("Task Reminder")
                .setContentText("You have a task to complete!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.baseline_done_24, "Mark as Done", donePendingIntent)
                .addAction(R.drawable.baseline_circle_notifications_24, "Snooze", snoozePendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Task Channel";
            String description = "Channel for task notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}