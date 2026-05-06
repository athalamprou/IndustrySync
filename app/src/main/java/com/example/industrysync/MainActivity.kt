package com.example.industrysync

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WorkOrderAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvWorkOrders)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter with an empty list first
        adapter = WorkOrderAdapter(emptyList())
        recyclerView.adapter = adapter

        // Now call the function to get real data
        fetchWorkOrders()
    }

    // Move the function OUTSIDE of onCreate
    private fun fetchWorkOrders() {
        RetrofitClient.instance.getWorkOrders().enqueue(object : Callback<List<WorkOrder>> {
            override fun onResponse(call: Call<List<WorkOrder>>, response: Response<List<WorkOrder>>) {
                if (response.isSuccessful) {
                    val orders = response.body() ?: emptyList()
                    adapter.updateData(orders)
                } else {
                    Toast.makeText(this@MainActivity, "Server error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<WorkOrder>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to connect: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}