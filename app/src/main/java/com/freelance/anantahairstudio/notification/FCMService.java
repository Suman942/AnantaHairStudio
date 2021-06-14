package com.freelance.anantahairstudio.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.myBooking.OnGoingBookingActivity;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FCMService extends FirebaseMessagingService {

    public FCMService() {
    }

//    @Override
//    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        showNotifications();
//    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("token"," "+token);
    }

    @Override
    public void handleIntent(Intent intent) {
        Log.d( "FCM", "handleIntent ");
        showNotifications();
    }

    public  void showNotifications(){
        Intent intent = new Intent(this, OnGoingBookingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"AnantaHairStudioNotification")
                .setSmallIcon(R.drawable.main_logo)
                .setContentTitle("Hey! Congratulations")
                .setContentText("Your appointment request is accepted")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setVibrate(new long[]{0L})
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(99 /* ID of notification */, builder.build());

    }
}