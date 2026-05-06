package com.example.industrysync

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddWorkOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_work_order)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val spinnerPriority = findViewById<Spinner>(R.id.spinnerPriority)

        // Setup the Priority Spinner
        val priorities = arrayOf("Low", "Medium", "High", "Critical")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPriority.adapter = adapter

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDescription.text.toString()
            val priority = spinnerPriority.selectedItem.toString()

            if (title.isEmpty()) {
                etTitle.error = "Title required"
                return@setOnClickListener
            }

            val newOrder = WorkOrder(
                title = title,
                description = desc,
                priority = priority,
                isCompleted = false
            )

            sendToServer(newOrder)
        }
    }

    private fun sendToServer(order: WorkOrder) {
        RetrofitClient.instance.createWorkOrder(order).enqueue(object : Callback<WorkOrder> {
            override fun onResponse(call: Call<WorkOrder>, response: Response<WorkOrder>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddWorkOrderActivity, "Task Synced to Cloud!", Toast.LENGTH_SHORT).show()
                    finish() // Close this screen and go back
                } else {
                    Toast.makeText(this@AddWorkOrderActivity, "Fail: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WorkOrder>, t: Throwable) {
                Toast.makeText(this@AddWorkOrderActivity, "Network Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}