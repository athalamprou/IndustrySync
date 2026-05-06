package com.example.industrysync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkOrderAdapter(private var workOrders: List<WorkOrder>) :
    RecyclerView.Adapter<WorkOrderAdapter.WorkOrderViewHolder>() {

    // 1. Describe the "Box" that holds each item
    class WorkOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPriority: TextView = view.findViewById(R.id.tvPriority)
    }

    // 2. Inflate the XML layout for each row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkOrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_work_order, parent, false)
        return WorkOrderViewHolder(view)
    }

    // 3. Put the data into the views
    override fun onBindViewHolder(holder: WorkOrderViewHolder, position: Int) {
        val order = workOrders[position]
        holder.tvTitle.text = order.title
        holder.tvPriority.text = "Priority: ${order.priority}"
    }

    override fun getItemCount() = workOrders.size

    // Helper to refresh the list later when API returns data
    fun updateData(newOrders: List<WorkOrder>) {
        this.workOrders = newOrders
        notifyDataSetChanged()
    }
}