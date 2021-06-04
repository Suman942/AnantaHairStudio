package com.freelance.anantahairstudio.cart.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.cart.pojo.AddtoCartResponse;
import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartRepo {
    private static AddToCartRepo addToCartRepo;
    private ApiInterface apiInterface;

    public AddToCartRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static AddToCartRepo getInstance() {
        if (addToCartRepo == null) {
            addToCartRepo = new AddToCartRepo();
        }
        return addToCartRepo;
    }

    public void addToCart(String token, String serviceID, String individuals, MutableLiveData<AddtoCartResponse> mutableLiveData){
        apiInterface.addToCart(token,serviceID,individuals).enqueue(new Callback<AddtoCartResponse>() {
            @Override
            public void onResponse(Call<AddtoCartResponse> call, Response<AddtoCartResponse> response) {
                if (response.code() == 200){
                    Log.i("addToCart","successful");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AddtoCartResponse> call, Throwable t) {
                Log.i("addToCart","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
}
