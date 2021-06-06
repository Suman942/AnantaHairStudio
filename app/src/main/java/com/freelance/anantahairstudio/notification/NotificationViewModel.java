package com.freelance.anantahairstudio.notification;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

public class NotificationViewModel extends ViewModel {
    MutableLiveData<FcmResponse> notificationLiveData = new MutableLiveData<>();

    public void sendNotification(JsonObject jsonObject){
        NotificationRepository.getInstance().sendNotification(jsonObject,notificationLiveData);
    }
    public MutableLiveData<FcmResponse> sendNotificationLiveData(){
        return notificationLiveData;
    }
}
