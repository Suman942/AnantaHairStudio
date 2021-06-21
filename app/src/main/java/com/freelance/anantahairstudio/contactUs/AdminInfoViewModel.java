package com.freelance.anantahairstudio.contactUs;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminInfoViewModel extends ViewModel {

    MutableLiveData<ContactUpdateResponse> contactUpdateResponseMutableLiveData = new MutableLiveData<>();


    public void getAdminDetails(String token){
        AdminUpdateInfoRepo.getInstance().adminInfo(token,contactUpdateResponseMutableLiveData);

    }
    public MutableLiveData<ContactUpdateResponse> getAdminDetailsLiveData(){
        return contactUpdateResponseMutableLiveData;
    }
}
