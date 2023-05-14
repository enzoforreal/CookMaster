package com.example.cookmaster_app_android.data.api;

import com.example.cookmaster_app_android.data.model.LoginRequest;
import com.example.cookmaster_app_android.data.model.RegisterRequest;
import com.example.cookmaster_app_android.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    // Methode d'authentification
    @POST("auth/login")
    Call<User> login(@Body LoginRequest loginRequest);

    @POST("register")
    Call<User> registerUser(@Body RegisterRequest request);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @POST("users")
    Call<Void> createUser(@Body User user);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int userId, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int userId);
}
