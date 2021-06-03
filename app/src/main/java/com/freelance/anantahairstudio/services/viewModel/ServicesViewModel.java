package com.freelance.anantahairstudio.services.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.services.pojo.ServicesResponse;
import com.freelance.anantahairstudio.services.repo.ServicesRepo;

public class ServicesViewModel extends ViewModel {

    MutableLiveData<ServicesResponse> servicesResponseMutableLiveData = new MutableLiveData<>();

    public void getAllServices(String token){
        ServicesRepo.getInstance().allServices(token,servicesResponseMutableLiveData);
    }
    public MutableLiveData<ServicesResponse> getAllServicesLiveData(){
        return  servicesResponseMutableLiveData;
    }
}

