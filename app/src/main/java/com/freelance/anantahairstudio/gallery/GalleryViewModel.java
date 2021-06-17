package com.freelance.anantahairstudio.gallery;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class GalleryViewModel extends ViewModel {
    MutableLiveData<FetchGalleryResponse> fetchGalleryResponseMutableLiveData = new MutableLiveData<>();


    public void fetchGalleryImg(String token){
        GalleryRepo.getInstance().fetchGalleryImg(token,fetchGalleryResponseMutableLiveData);
    }
    public MutableLiveData<FetchGalleryResponse> fetchGalleryImgLiveData(){
        return fetchGalleryResponseMutableLiveData;
    }
}
