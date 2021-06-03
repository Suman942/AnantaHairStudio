package com.freelance.anantahairstudio.network;


import com.freelance.anantahairstudio.myInfo.pojo.MyAccountResponse;
import com.freelance.anantahairstudio.profileedit.ppojo.UpdateData;
import com.freelance.anantahairstudio.services.pojo.ServicesResponse;
import com.freelance.anantahairstudio.signup.pojo.Authentication;

import retrofit2.Call;
import retrofit2.Callback;
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
}
