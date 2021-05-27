package com.freelance.anantahairstudio.network;

import com.freelance.anantahairstudio.utils.PrefManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //public static final String BASE_URL = "https://hello-ji-232307.appspot.com/api/";
    public static final String BASE_URL = ConstantUrl.BASE_API_URL;

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS);

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        httpClient.authenticator(new TokenAuthenticator());

//        httpClient.addInterceptor(new Interceptor() {
//            @NotNull
//            @Override
//            public Response intercept(@NotNull Chain chain) throws IOException {
//                Request newRequest = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + PrefManager.getInstance().getString(R.string.pref_netclan_auth_key))
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        });
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }


}
