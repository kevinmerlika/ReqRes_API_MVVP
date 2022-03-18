package com.example.peopleproject.repository

import com.example.peopleproject.network.RetrofitService

class ProfileRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getList() = retrofitService.getAllUsers()
}