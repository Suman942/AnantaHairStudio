package com.freelance.anantahairstudio.cart.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.cart.pojo.AddtoCartResponse;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.cart.pojo.RemoveCartResponse;
import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;

import javax.xml.transform.sax.SAXResult;

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

    public void getCartList(String token, MutableLiveData<CartListResponse> mutableLiveData){
        apiInterface.getCartList(token).enqueue(new Callback<CartListResponse>() {
            @Override
            public void onResponse(Call<CartListResponse> call, Response<CartListResponse> response) {
                if (response.code() == 200){
                    Log.i("getCart","successful");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CartListResponse> call, Throwable t) {
                Log.i("getCart","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }

    public void removeCart(String token, String removeId, MutableLiveData<RemoveCartResponse> mutableLiveData){
        apiInterface.removeCart(token,removeId).enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                if (response.code() == 200){
                    Log.i("removeCart","successful");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                Log.i("removeCart","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
}
