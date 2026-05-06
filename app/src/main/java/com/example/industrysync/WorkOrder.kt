package com.example.industrysync

import com.google.gson.annotations.SerializedName

data class WorkOrder(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("isCompleted") val isCompleted: Boolean = false
)