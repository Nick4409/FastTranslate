package com.dekhtiarenko.nick.translateontap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Nick on 05.02.2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 1;
    private static boolean START_PROGRAM = true;
    Context context = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;


        if(context!=null) {
            if (START_PROGRAM) {
                Toast.makeText(context, "Try to start service", Toast.LENGTH_SHORT).show();
                createNotification();
                context.startService(new Intent(context, ServiceListener.class));
            } else if (!START_PROGRAM) {
                Toast.makeText(context, "Try to stop service", Toast.LENGTH_SHORT).show();
                deleteableNotification();
                context.stopService(new Intent(context, ServiceListener.class));
            }

            }
        }




    void createNotification() {
        Intent intent = new Intent(context, NotificationReceiver.class);
        //intent.putExtra("ID", "stop");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_content_copy_black_48dp);
        builder.setContentTitle("Translate on Tap");
        builder.setContentText("Перекладач працює у фоні");
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setOngoing(true);
        builder.addAction(R.drawable.ic_close_black_24dp, "Вимкнути перекладач", pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
        START_PROGRAM=false;
    }

    void deleteableNotification() {
        Intent intent = new Intent(context, NotificationReceiver.class);
        //intent.putExtra("ID", "start");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_content_copy_black_48dp);
        builder.setContentTitle("Translate on Tap");
        builder.setContentText("Перекладач вимкнений");
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.addAction(R.drawable.ic_done_black_24dp, "Увімкнути перекладач", pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
        START_PROGRAM=true;
    }

}
