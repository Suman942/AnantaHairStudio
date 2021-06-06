package com.freelance.anantahairstudio.notification;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.cart.repo.AddToCartRepo;
import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {
    private static NotificationRepository notificationRepository;
    private ApiInterface apiInterface;

    public NotificationRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static NotificationRepository getInstance() {
        if (notificationRepository == null) {
            notificationRepository = new NotificationRepository();
        }
        return notificationRepository;
    }

    public void sendNotification(JsonObject jsonObject, MutableLiveData<FcmResponse> mutableLiveData){
        apiInterface.sendNotifications(jsonObject).enqueue(new Callback<FcmResponse>() {
            @Override
            public void onResponse(Call<FcmResponse> call, Response<FcmResponse> response) {
                if (response.isSuccessful()){
                    Log.i("notification","success");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FcmResponse> call, Throwable t) {
                Log.i("notification",""+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
}
