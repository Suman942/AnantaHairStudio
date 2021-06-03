package com.freelance.anantahairstudio.profileedit.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.profileedit.ppojo.UpdateData;
import com.freelance.anantahairstudio.profileedit.repo.UpdateRepo;

public class ProfileEditViewModel extends ViewModel {
    MutableLiveData<UpdateData> updateDataMutableLiveData = new MutableLiveData<>();

    public void updatePhone(String token,String phone){
        UpdateRepo.getInstance().updatePhone(token,phone,updateDataMutableLiveData);
    }
    public MutableLiveData<UpdateData> updatePhoneLiveData(){
        return updateDataMutableLiveData;
    }

    public void updateAddress(String token,String address){
        UpdateRepo.getInstance().updateAddress(token,address,updateDataMutableLiveData);
    }
}
