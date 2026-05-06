package com.example.industrysync

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Check your .NET console: use 5131 if it says http, or 7130 if only https
    private const val BASE_URL = "http://192.168.1.87:5105/"

    val instance: WorkOrderApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(WorkOrderApi::class.java)
    }
}