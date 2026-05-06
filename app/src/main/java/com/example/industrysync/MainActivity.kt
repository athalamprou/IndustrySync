package com.example.industrysync

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WorkOrderAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Find the RecyclerView from the layout
        recyclerView = findViewById(R.id.rvWorkOrders)

        // 2. Set the LayoutManager (Vertical list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. Create dummy data for testing
        val dummyData = listOf(
            WorkOrder(1, "Fix Engine", "Check oil leak", "High"),
            WorkOrder(2, "Monthly Service", "Routine checkup", "Medium"),
            WorkOrder(3, "Paint Wall", "Office area", "Low")
        )

        // 4. Initialize and attach the adapter
        adapter = WorkOrderAdapter(dummyData)
        recyclerView.adapter = adapter
    }
}