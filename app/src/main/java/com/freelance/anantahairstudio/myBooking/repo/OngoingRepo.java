package com.freelance.anantahairstudio.myBooking.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.myBooking.pojo.BookingDetailsResponse;
import com.freelance.anantahairstudio.myBooking.pojo.PaymentDoneResponse;
import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;
import com.freelance.anantahairstudio.myBooking.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;

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


    public void ongoingService(String token,String fetch,String page, MutableLiveData<OnGoingServiceResponse> mutableLiveData){
        apiInterface.ongoingService(token,fetch,page).enqueue(new Callback<OnGoingServiceResponse>() {
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

    public void bookingDetails(String fetchDetails, MutableLiveData<BookingDetailsResponse> mutableLiveData){
        apiInterface.allBookingDetails(fetchDetails).enqueue(new Callback<BookingDetailsResponse>() {
            @Override
            public void onResponse(Call<BookingDetailsResponse> call, Response<BookingDetailsResponse> response) {
                if (response.code() ==200){
                    Log.i("bookingDetails","success");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BookingDetailsResponse> call, Throwable t) {
                Log.i("bookingDetails","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }

    public void paymentDone(String token, String bookingId, String amount, String points, MutableLiveData<PaymentDoneResponse> mutableLiveData){
        apiInterface.paymentDone(token,bookingId,amount,points).enqueue(new Callback<PaymentDoneResponse>() {
            @Override
            public void onResponse(Call<PaymentDoneResponse> call, Response<PaymentDoneResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PaymentDoneResponse> call, Throwable t) {
            mutableLiveData.setValue(null);
            }
        });
    }
}
