package com.freelance.anantahairstudio.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.ongoingServices.OnGoingBookingActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

    public FCMService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i("FCM","onMessageReceived");
        showNotifications(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("token"," "+token);
    }

//    @Override
//    public void handleIntent(Intent intent) {
//        Log.i( "FCM", "handleIntent ");
//        showNotifications();
//    }
//
//    public  void showNotifications(){
//        Intent intent = new Intent(this, OnGoingBookingActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"AnantaHairStudioNotification")
//                .setSmallIcon(R.drawable.main_logo)
//                .setContentTitle("Hey! Congratulations")
//                .setContentText("Your appointment request is accepted")
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
//                .setVibrate(new long[]{0L})
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(false);
//
//        NotificationManager notificationManager =
//                (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(99 /* ID of notification */, builder.build());
//
//    }

    public  void showNotifications(String title,String message){
        Intent intent = new Intent(this, OnGoingBookingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"AnantaHairStudioNotification")
                .setSmallIcon(R.drawable.main_logo)
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setVibrate(new long[]{0L})
                .setContentIntent(pendingIntent)
                .setAutoCancel(false);

        NotificationManager notificationManager =
                (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(99 /* ID of notification */, builder.build());

    }

}