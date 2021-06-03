package com.freelance.anantahairstudio.services.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;
import com.freelance.anantahairstudio.services.pojo.ServicesResponse;
import com.freelance.anantahairstudio.signup.repo.AuthenticationRepo;
import com.freelance.anantahairstudio.utils.ErrorResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesRepo {
    private static ServicesRepo servicesRepo;
    private ApiInterface apiInterface;



    public ServicesRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static ServicesRepo getInstance() {
        if (servicesRepo == null) {
            servicesRepo = new ServicesRepo();
        }
        return servicesRepo;
    }

    public void allServices(String token, MutableLiveData<ServicesResponse> mutableLiveData)
    {
        apiInterface.services(token).enqueue(new Callback<ServicesResponse>() {
            @Override
            public void onResponse(Call<ServicesResponse> call, Response<ServicesResponse> response) {
                if (response.code() == 200){
                    Log.i("services","successful");
                    mutableLiveData.setValue(response.body());
                }
                else {
                    ErrorResponse errorResponse = null;
                    try {
                        Log.e("Error", "code: " + response.code() + " body: " + response.body() + " message: " + response.message() + " errorBody: " + response.errorBody());
                        errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        Log.e("Error", "code: " + response.code() + " error: " + errorResponse.getError() + " errorMsg: " + errorResponse.getErrorMsg());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServicesResponse> call, Throwable t) {
                Log.i("services",""+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
}
