package com.profile.Retrofit

import com.profile.Model.Home_Response
import com.profile.Model.Profile_Response
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @GET("profile")
    fun getProfile(): Call<Profile_Response>


    @GET("home")
    fun getHome(): Call<Home_Response>


}