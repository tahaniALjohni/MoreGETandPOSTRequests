package com.example.moregetandpostrequests

import retrofit2.Call
import retrofit2.http.*



interface APIInterface {
    @GET("/test/")
    fun getUser(): Call<List<Data.UsersDataItem>>


    @POST("/test/")
    fun addUser(@Body userData:Data.UsersDataItem): Call<Data.UsersDataItem>
}