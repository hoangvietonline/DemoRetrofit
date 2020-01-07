package com.hoangviet.demoui.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static String STACKOVERFLOW_URL = "https://api.stackexchange.com/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(STACKOVERFLOW_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S getStackOverFlowAPI(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
