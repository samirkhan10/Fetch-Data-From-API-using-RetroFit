package com.dzo.fetchdatafromapiusingretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface myInterface {

    @GET("posts")
    Call<List<myModel>> fetchData();

}
