package com.freelance.anantahairstudio.myInfo.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.myInfo.pojo.MyAccountResponse;
import com.freelance.anantahairstudio.myInfo.repo.MyAccountRepo;

public class MyAccountInfoViewModel extends ViewModel {
    MutableLiveData<MyAccountResponse> myAccountResponseMutableLiveData = new MutableLiveData<>();
    public void myAccount(String token){
        MyAccountRepo.getInstance().myAccountInfo(token,myAccountResponseMutableLiveData);
    }

    public MutableLiveData<MyAccountResponse> myAccountLiveData(){
        return myAccountResponseMutableLiveData;
    }
}
