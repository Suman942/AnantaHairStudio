package com.freelance.anantahairstudio.myInfo.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.myInfo.pojo.MyAccountResponse;
import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccountRepo {

    private static MyAccountRepo myAccountRepo;
    private ApiInterface apiInterface;

    public MyAccountRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static MyAccountRepo getInstance() {
        if (myAccountRepo == null) {
            myAccountRepo = new MyAccountRepo();}
        return myAccountRepo;
    }

    public void myAccountInfo(String token, MutableLiveData<MyAccountResponse> mutableLiveData){
        apiInterface.myAccount(token).enqueue(new Callback<MyAccountResponse>() {
            @Override
            public void onResponse(Call<MyAccountResponse> call, Response<MyAccountResponse> response) {
                if (response.code() == 200){
                    Log.i("myaccount","success");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MyAccountResponse> call, Throwable t) {
                Log.i("updateph","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
}
