package com.freelance.anantahairstudio.cart.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudio.cart.pojo.AddtoCartResponse;
import com.freelance.anantahairstudio.cart.pojo.BookingResponse;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.cart.pojo.RemoveCartResponse;
import com.freelance.anantahairstudio.cart.repo.AddToCartRepo;

public class AddToCartViewModel extends ViewModel {
    MutableLiveData<AddtoCartResponse> addtoCardResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<CartListResponse> cartListResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<RemoveCartResponse> removeCartResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<BookingResponse> bookingResponseMutableLiveData = new MutableLiveData<>();

    public void addToCart(String token,String serviceId,String individuals){
        AddToCartRepo.getInstance().addToCart(token,serviceId,individuals,addtoCardResponseMutableLiveData);
    }

    public MutableLiveData<AddtoCartResponse> addTOCartLiveData(){
        return addtoCardResponseMutableLiveData;
    }

    public void getCartList(String token){
        AddToCartRepo.getInstance().getCartList(token,cartListResponseMutableLiveData);
    }

    public MutableLiveData<CartListResponse> getCartListLiveData(){
        return  cartListResponseMutableLiveData;
    }

    public void removeCart(String token,String removeCartId){
        AddToCartRepo.getInstance().removeCart(token,removeCartId,removeCartResponseMutableLiveData);
    }

    public MutableLiveData<RemoveCartResponse> removeCartLiveData(){
        return removeCartResponseMutableLiveData;
    }

    public void booking(String token,String slot,String book){
        AddToCartRepo.getInstance().booking(token,slot,book,bookingResponseMutableLiveData);
    }

    public MutableLiveData<BookingResponse> bookingLiveData(){
        return  bookingResponseMutableLiveData;
    }
}
