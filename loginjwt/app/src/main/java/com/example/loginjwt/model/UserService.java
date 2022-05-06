package com.example.loginjwt.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("login")
    Call<JwtResponse> login(@Body UserRequest userRequest);
}
