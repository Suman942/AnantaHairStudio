package com.freelance.anantahairstudio.profileedit.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;
import com.freelance.anantahairstudio.profileedit.ppojo.UpdateData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRepo {
    private static UpdateRepo updateRepo;
    private ApiInterface apiInterface;

    public UpdateRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static UpdateRepo getInstance() {
        if (updateRepo == null) {
            updateRepo = new UpdateRepo();}
        return updateRepo;
    }

    public void updatePhone(String token, String phone, MutableLiveData<UpdateData> mutableLiveData){
        apiInterface.updatePhone(token,phone).enqueue(new Callback<UpdateData>() {
            @Override
            public void onResponse(Call<UpdateData> call, Response<UpdateData> response) {
                if (response.code() == 200){
                    Log.i("updateph","success");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UpdateData> call, Throwable t) {
                Log.i("updateph","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
    public void updateAddress(String token, String address, MutableLiveData<UpdateData> mutableLiveData){
        apiInterface.updateAddress(token,address).enqueue(new Callback<UpdateData>() {
            @Override
            public void onResponse(Call<UpdateData> call, Response<UpdateData> response) {
                if (response.code() == 200){
                    Log.i("updateAdd","success");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UpdateData> call, Throwable t) {
                Log.i("updateAdd","failure: "+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }

}
