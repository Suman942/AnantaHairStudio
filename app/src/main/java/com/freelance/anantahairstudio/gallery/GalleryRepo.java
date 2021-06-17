package com.freelance.anantahairstudio.gallery;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.freelance.anantahairstudio.network.ApiClient;
import com.freelance.anantahairstudio.network.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryRepo {
    private static GalleryRepo galleryRepo;
    private ApiInterface apiInterface;

    public GalleryRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static GalleryRepo getInstance() {
        if (galleryRepo == null) {
            galleryRepo = new GalleryRepo();
        }
        return galleryRepo;
    }


    public void fetchGalleryImg(String token,MutableLiveData<FetchGalleryResponse> mutableLiveData){
        apiInterface.getGalleryPic(token).enqueue(new Callback<FetchGalleryResponse>() {
            @Override
            public void onResponse(Call<FetchGalleryResponse> call, Response<FetchGalleryResponse> response) {
                if (response.code() == 200){
                    Log.i("gallery","success");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FetchGalleryResponse> call, Throwable t) {
                Log.i("gallery",""+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }
}
