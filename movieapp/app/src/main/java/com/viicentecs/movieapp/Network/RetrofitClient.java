package com.viicentecs.movieapp.Network;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viicentecs.movieapp.Utils.NetworkConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(NetworkConstants.url_base)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


    public static GlideUrl getQRUrlWithHeaders(String movieUrl) {
        String url = NetworkConstants.url_image_base + movieUrl;
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", NetworkConstants.api_key)
                .build());
    }

}
