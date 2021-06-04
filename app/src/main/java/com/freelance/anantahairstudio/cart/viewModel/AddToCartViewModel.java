package com.freelance.anantahairstudio.cart.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.cart.pojo.AddtoCartResponse;
import com.freelance.anantahairstudio.cart.repo.AddToCartRepo;

public class AddToCartViewModel extends ViewModel {
    MutableLiveData<AddtoCartResponse> addtoCardResponseMutableLiveData = new MutableLiveData<>();

    public void addToCart(String token,String serviceId,String individuals){
        AddToCartRepo.getInstance().addToCart(token,serviceId,individuals,addtoCardResponseMutableLiveData);
    }

    public MutableLiveData<AddtoCartResponse> addTOCartLiveData(){
        return addtoCardResponseMutableLiveData;
    }
}
