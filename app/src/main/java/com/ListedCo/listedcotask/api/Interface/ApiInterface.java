package com.ListedCo.listedcotask.api.Interface;

import com.ListedCo.listedcotask.api.response.dashboardNew;

import retrofit2.Call;
import retrofit2.http.GET;

/***
 *Created By Mayank
 **/
public interface ApiInterface {
    String BASE_URL = "https://api.inopenapp.com/api/v1/";

    @GET("dashboardNew")
    Call<dashboardNew> dashboardNew();

}
