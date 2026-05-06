package com.example.industrysync

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WorkOrderApi {
        @GET("api/WorkOrders")
        fun getWorkOrders(): Call<List<WorkOrder>>

        @POST("api/WorkOrders")
        fun createWorkOrder(@Body workOrder: WorkOrder): Call<WorkOrder>

        @PUT("api/WorkOrders/{id}")
        fun updateWorkOrder(@Path("id") id: Long, @Body workOrder: WorkOrder): Call<Void>

    }
