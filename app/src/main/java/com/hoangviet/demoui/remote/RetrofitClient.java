package com.hoangviet.demoui.remote;

import com.hoangviet.demoui.model.MyPojo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClient {
    @GET("2.2/search")
    Call<MyPojo> getQuestion(@Query("order") String order, @Query("sort") String sort, @Query("tagged") String tagged, @Query("site") String site);

    @DELETE("2.2")
    Call<ResponseBody> deleteRetrofit();
}
