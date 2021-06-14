package com.freelance.anantahairstudio.myBooking.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.myBooking.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.myBooking.repo.OngoingRepo;

public class OngoingServiceViewModel extends ViewModel {
    MutableLiveData<OnGoingServiceResponse> goingServiceResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<CancelBookingResponse> cancelBookingResponseMutableLiveData = new MutableLiveData<>();

    public void ongoingService(String token){
        OngoingRepo.getInstance().ongoingService(token,goingServiceResponseMutableLiveData);
    }
    public MutableLiveData<OnGoingServiceResponse> ongoingServiceLiveData(){
        return  goingServiceResponseMutableLiveData;
    }

    public void cancelBooking(String token,String bookingId){
        OngoingRepo.getInstance().cancelBooking(token,bookingId,cancelBookingResponseMutableLiveData);
    }
    public MutableLiveData<CancelBookingResponse> cancelBookingLiveData(){
        return cancelBookingResponseMutableLiveData;
    }
}
