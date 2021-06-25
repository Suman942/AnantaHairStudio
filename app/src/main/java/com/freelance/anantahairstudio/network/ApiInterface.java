package com.freelance.anantahairstudio.network;


import com.freelance.anantahairstudio.contactUs.ContactUpdateResponse;
import com.freelance.anantahairstudio.cart.pojo.AddtoCartResponse;
import com.freelance.anantahairstudio.cart.pojo.BookingResponse;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.cart.pojo.RemoveCartResponse;
import com.freelance.anantahairstudio.gallery.FetchGalleryResponse;
import com.freelance.anantahairstudio.myBooking.pojo.BookingDetailsResponse;
import com.freelance.anantahairstudio.myInfo.pojo.MyAccountResponse;
import com.freelance.anantahairstudio.notification.FcmResponse;
import com.freelance.anantahairstudio.myBooking.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.profileedit.ppojo.UpdateData;
import com.freelance.anantahairstudio.referal.pojo.ReferalResponse;
import com.freelance.anantahairstudio.services.pojo.ServicesResponse;
import com.freelance.anantahairstudio.signup.pojo.Authentication;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("login")
    Call<Authentication> authentication(
            @Field("email") String email,
            @Field("name") String name
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
    @GET("cart?fetch=1")
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
    @GET("booking?")
    Call<OnGoingServiceResponse> ongoingService(
            @Header("Authorization") String token,
            @Query("fetch") String fetch,
            @Query("page") String page
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

    @Headers({
            "Content-Type: application/json",
            "Authorization:key=AAAAlZCQ3F8:APA91bEKy8G_coXnOmT7-kkvnDX4YtKqkVetcF7iawq8ICHKnX5YOoAsXzd3sTkaOMEt-eaDo69GqOCOyvxCSdyBD8N0VCKfUcRyAdkqSSUufUyz8tA0n-nqBYwZ96e9PJyZgid_NfJb"
    })
    @POST("https://fcm.googleapis.com/fcm/send")
    Call<FcmResponse> sendNotifications(
            @Body JsonObject jsonObject
            );


    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("fetch?gallery")
    Call<FetchGalleryResponse> getGalleryPic(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("fetch?business_info=1")
    Call<ContactUpdateResponse> getAdminDetails(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("admin/booking?")
    Call<BookingDetailsResponse> allBookingDetails(
            @Query("fetch_details") String fetch
    );

//    @Headers({
//            "Content-Type: application/x-www-form-urlencoded",
//            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
//    })
//    @GET("payment")
//    Call<> paymentDone();


}
