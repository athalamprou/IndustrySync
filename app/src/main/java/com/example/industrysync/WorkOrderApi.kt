package com.example.industrysync

import retrofit2.Call
import retrofit2.http.GET

interface WorkOrderApi {
    @GET("api/WorkOrders")
    fun getWorkOrders(): Call<List<WorkOrder>>
}