package com.freelance.anantahairstudio.network;

import android.util.Log;



import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;

public class TokenAuthenticator implements Authenticator {

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

//        apiInterface.reissueAuth(PrefManager.getInstance().getString(R.string.pref_netclan_refresh_key))
//                .enqueue(new Callback<AuthItem>() {
//                    @Override
//                    public void onResponse(Call<AuthItem> call, retrofit2.Response<AuthItem> response) {
//                        if (response != null && response.code() == 200) {
//                            PrefManager.getInstance().putString(R.string.pref_netclan_auth_key, response.body().getAuthKey());
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<AuthItem> call, Throwable t) {
//                        Log.d("TokenAuthenticator", "onFailure ::" + t.getMessage());
//                    }
//                });

        return null;
    }
}
