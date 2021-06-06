package com.freelance.anantahairstudio.ongoingServices.repo;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.cart.repo.AddToCartRepo;
import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;
import com.freelance.anantahairstudio.ongoingServices.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.ongoingServices.pojo.OnGoingServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngoingRepo {
    private static OngoingRepo ongoingRepo;
    private ApiInterface apiInterface;

    public OngoingRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static OngoingRepo getInstance() {
        if (ongoingRepo == null) {
            ongoingRepo = new OngoingRepo();
        }
        return ongoingRepo;
    }


    public void ongoingService(String token, MutableLiveData<OnGoingServiceResponse> mutableLiveData){
        apiInterface.ongoingService(token).enqueue(new Callback<OnGoingServiceResponse>() {
            @Override
            public void onResponse(Call<OnGoingServiceResponse> call, Response<OnGoingServiceResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<OnGoingServiceResponse> call, Throwable t) {
                    mutableLiveData.setValue(null);
            }
        });
    }

    public void cancelBooking(String token, String bookingId, MutableLiveData<CancelBookingResponse> mutableLiveData){
        apiInterface.cancelBooking(token, bookingId).enqueue(new Callback<CancelBookingResponse>() {
            @Override
            public void onResponse(Call<CancelBookingResponse> call, Response<CancelBookingResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CancelBookingResponse> call, Throwable t) {
                mutableLiveData.setValue(null);

            }
        });
    }
}
