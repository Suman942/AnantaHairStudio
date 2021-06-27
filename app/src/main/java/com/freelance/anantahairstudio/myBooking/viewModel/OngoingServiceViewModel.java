package com.freelance.anantahairstudio.myBooking.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.myBooking.pojo.BookingDetailsResponse;
import com.freelance.anantahairstudio.myBooking.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.myBooking.pojo.PaymentDoneResponse;
import com.freelance.anantahairstudio.myBooking.repo.OngoingRepo;

public class OngoingServiceViewModel extends ViewModel {
    MutableLiveData<OnGoingServiceResponse> goingServiceResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<CancelBookingResponse> cancelBookingResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<BookingDetailsResponse> bookingDetailsResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<PaymentDoneResponse> paymentDoneResponseMutableLiveData = new MutableLiveData<>();

    public void paymentDone(String token,String bookingId,String amount,String points){
        OngoingRepo.getInstance().paymentDone(token,bookingId,amount,points,paymentDoneResponseMutableLiveData);
    }
    public MutableLiveData<PaymentDoneResponse> paymentDoneLiveData(){
        return paymentDoneResponseMutableLiveData;
    }

    public void ongoingService(String token, String fetch,String page){
        OngoingRepo.getInstance().ongoingService(token,fetch,page,goingServiceResponseMutableLiveData);
    }
    public MutableLiveData<OnGoingServiceResponse> ongoingServiceLiveData(){
        return  goingServiceResponseMutableLiveData;
    }

    public void bookingDetails(String fetchDetails){
        OngoingRepo.getInstance().bookingDetails(fetchDetails,bookingDetailsResponseMutableLiveData);
    }
    public MutableLiveData<BookingDetailsResponse> bookingDetailsLiveData(){
        return bookingDetailsResponseMutableLiveData;
    }

    public void cancelBooking(String token,String bookingId){
        OngoingRepo.getInstance().cancelBooking(token,bookingId,cancelBookingResponseMutableLiveData);
    }
    public MutableLiveData<CancelBookingResponse> cancelBookingLiveData(){
        return cancelBookingResponseMutableLiveData;
    }
}
