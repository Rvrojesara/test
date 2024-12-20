package com.example.practice_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("ACTION_MARK_DONE".equals(action)) {
            Toast.makeText(context, "Task marked as done!", Toast.LENGTH_SHORT).show();
        } else if ("ACTION_SNOOZE".equals(action)) {
            Toast.makeText(context, "Task snoozed for later!", Toast.LENGTH_SHORT).show();
        }
    }
}
