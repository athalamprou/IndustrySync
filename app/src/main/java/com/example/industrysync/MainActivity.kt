package com.example.industrysync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WorkOrderAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvWorkOrders)
        fabAdd = findViewById(R.id.fabAdd)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // FIX: Passing the lambda block { updatedOrder -> ... } as the onStatusChanged parameter
        adapter = WorkOrderAdapter(emptyList()) { updatedOrder ->
            updateTaskStatus(updatedOrder)
        }

        recyclerView.adapter = adapter

        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddWorkOrderActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        fetchWorkOrders()
    }

    private fun fetchWorkOrders() {
        RetrofitClient.instance.getWorkOrders().enqueue(object : Callback<List<WorkOrder>> {
            override fun onResponse(call: Call<List<WorkOrder>>, response: Response<List<WorkOrder>>) {
                if (response.isSuccessful) {
                    val orders = response.body() ?: emptyList()
                    adapter.updateData(orders)
                }
            }
            override fun onFailure(call: Call<List<WorkOrder>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateTaskStatus(order: WorkOrder) {
        RetrofitClient.instance.updateWorkOrder(order.id, order).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Refresh the list so the item moves between "Pending" and "Completed"
                    fetchWorkOrders()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to update", Toast.LENGTH_SHORT).show()
            }
        })
    }
}