package com.example.industrysync

data class WorkOrder(
    val id: Long = 0,
    val title: String,
    val description: String,
    val priority: String,
    val isCompleted: Boolean = false
)