package com.freelance.anantahairstudio.contactUs;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminUpdateInfoRepo {
    private static AdminUpdateInfoRepo adminUpdateInfoRepo;
    private ApiInterface apiInterface;



    public AdminUpdateInfoRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static AdminUpdateInfoRepo getInstance() {
        if (adminUpdateInfoRepo == null) {
            adminUpdateInfoRepo = new AdminUpdateInfoRepo();
        }
        return adminUpdateInfoRepo;
    }



    public void adminInfo(String token,MutableLiveData<ContactUpdateResponse> mutableLiveData){
        apiInterface.getAdminDetails(token).enqueue(new Callback<ContactUpdateResponse>() {
            @Override
            public void onResponse(Call<ContactUpdateResponse> call, Response<ContactUpdateResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactUpdateResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }
}
