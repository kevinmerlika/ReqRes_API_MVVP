package com.example.peopleproject.network

import com.example.peopleproject.data.ProfileResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("users?page=1")
    suspend fun getAllUsers() :Response<ProfileResponse>

    companion object{
        var retrofitService : RetrofitService? =null

        fun getInstance(): RetrofitService {

            if(retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)

            }
            return retrofitService!!
        }
    }
}