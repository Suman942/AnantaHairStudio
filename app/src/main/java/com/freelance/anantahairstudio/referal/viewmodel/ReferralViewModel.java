package com.freelance.anantahairstudio.referal.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.referal.pojo.ReferalResponse;
import com.freelance.anantahairstudio.referal.repo.ReferralCodeRepo;

public class ReferralViewModel extends ViewModel {

    MutableLiveData<ReferalResponse> responseMutableLiveData = new MutableLiveData<>();

    public void referralCode(String token, String code){
        ReferralCodeRepo.getInstance().referralCode(token,code,responseMutableLiveData);
    }

    public MutableLiveData<ReferalResponse> referralCodeLiveData(){
        return  responseMutableLiveData;
    }
}
