package com.freelance.anantahairstudio.signup.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;
import com.freelance.anantahairstudio.signup.pojo.Authentication;
import com.freelance.anantahairstudio.utils.ErrorResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationRepo {
    private static AuthenticationRepo authenticationRepo;
    private ApiInterface apiInterface;



    public AuthenticationRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static AuthenticationRepo getInstance() {
        if (authenticationRepo == null) {
            authenticationRepo = new AuthenticationRepo();
        }
        return authenticationRepo;
    }

    public void authentication(String email, MutableLiveData<Authentication> mutableLiveData){
        apiInterface.authentication(email).enqueue(new Callback<Authentication>() {
            @Override
            public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                if (response.code() == 200){
                    Log.i("authentication","successfully authenticated");
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
            public void onFailure(Call<Authentication> call, Throwable t) {

                    Log.i("authentication","failure: "+t.getMessage());
                    mutableLiveData.setValue(null);

            }
        });

    }
}
