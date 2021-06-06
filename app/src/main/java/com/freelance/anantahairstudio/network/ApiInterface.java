package com.freelance.anantahairstudio.network;


import com.freelance.anantahairstudio.cart.pojo.AddtoCartResponse;
import com.freelance.anantahairstudio.cart.pojo.BookingResponse;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.cart.pojo.RemoveCartResponse;
import com.freelance.anantahairstudio.myInfo.pojo.MyAccountResponse;
import com.freelance.anantahairstudio.ongoingServices.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.ongoingServices.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.profileedit.ppojo.UpdateData;
import com.freelance.anantahairstudio.referal.pojo.ReferalResponse;
import com.freelance.anantahairstudio.services.pojo.ServicesResponse;
import com.freelance.anantahairstudio.signup.pojo.Authentication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("login")
    Call<Authentication> authentication(
            @Field("email") String email
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("services")
    Call<ServicesResponse> services(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("update")
    Call<UpdateData> updatePhone(
            @Header("Authorization") String token,
            @Field("phone") String phone
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("update")
    Call<UpdateData> updateAddress(
            @Header("Authorization") String token,
            @Field("address") String address
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("fetch?account")
    Call<MyAccountResponse> myAccount(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("referral")
    Call<ReferalResponse> referralCode(
            @Header("Authorization") String token,
            @Field("code") String code
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("cart")
    Call<AddtoCartResponse> addToCart(
            @Header("Authorization") String token,
            @Field("service_id") String serviceID,
            @Field("individuals") String individuals
    );


    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("cart?fetch")
    Call<CartListResponse> getCartList(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("cart")
    Call<RemoveCartResponse> removeCart(
            @Header("Authorization") String token,
            @Field("remove") String removeCartId
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("booking")
    Call<BookingResponse> booking(
            @Header("Authorization") String token,
            @Field("slot") String slot,
            @Field("book") String book
            );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("booking?fetch=1")
    Call<OnGoingServiceResponse> ongoingService(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("booking")
    Call<CancelBookingResponse> cancelBooking(
            @Header("Authorization") String token,
            @Field("cancel") String bookingId
    );

}
