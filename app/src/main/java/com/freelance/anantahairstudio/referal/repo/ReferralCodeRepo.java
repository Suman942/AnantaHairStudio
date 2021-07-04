package com.freelance.anantahairstudio.referal.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;
import com.freelance.anantahairstudio.referal.pojo.ReferalResponse;
import com.freelance.anantahairstudio.signup.repo.AuthenticationRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferralCodeRepo {
    private static ReferralCodeRepo referralCodeRepo;
    private ApiInterface apiInterface;

    public ReferralCodeRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static ReferralCodeRepo getInstance() {
        if (referralCodeRepo == null) {
            referralCodeRepo = new ReferralCodeRepo();
        }
        return referralCodeRepo;
    }

    public void referralCode(String token, String code, MutableLiveData<ReferalResponse> mutableLiveData){
        apiInterface.referralCode(token,code).enqueue(new Callback<ReferalResponse>() {
            @Override
            public void onResponse(Call<ReferalResponse> call, Response<ReferalResponse> response) {
                if (response.code() == 200){
                    Log.i("referral","successfully authenticated");
                    mutableLiveData.setValue(response.body());
                }
                if (response.code() == 400){
                    Log.i("referral","error");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ReferalResponse> call, Throwable t) {
                Log.i("referral","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
}
