package com.example.empty_news.network;

import com.example.empty_news.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/api/users/register")
    Call<String> registerUser(@Body User user);
}
