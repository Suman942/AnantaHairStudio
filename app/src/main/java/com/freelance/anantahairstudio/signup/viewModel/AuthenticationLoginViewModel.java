package com.freelance.anantahairstudio.signup.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.signup.pojo.Authentication;
import com.freelance.anantahairstudio.signup.repo.AuthenticationRepo;
import com.google.gson.JsonObject;

public class AuthenticationLoginViewModel extends ViewModel {
    private MutableLiveData<Authentication> authenticationLiveData = new MutableLiveData<>();


    public void authentication(String email) {
        AuthenticationRepo.getInstance().authentication(email, authenticationLiveData);
    }

    public MutableLiveData<Authentication> authenticationLiveData(){
        return  authenticationLiveData;
    }
}
