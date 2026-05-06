package com.example.industrysync

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkOrderAdapter(
    private var workOrders: List<WorkOrder>,
    private val onStatusChanged: (WorkOrder) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    private var displayList = mutableListOf<Any>()

    init {
        updateList()
    }

    fun updateData(newOrders: List<WorkOrder>) {
        this.workOrders = newOrders
        updateList()
        notifyDataSetChanged()
    }

    private fun updateList() {
        displayList.clear()
        val pending = workOrders.filter { !it.isCompleted }
        val completed = workOrders.filter { it.isCompleted }

        if (pending.isNotEmpty()) {
            displayList.add("Pending")
            displayList.addAll(pending)
        }
        if (completed.isNotEmpty()) {
            displayList.add("Completed tasks")
            displayList.addAll(completed)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (displayList[position] is String) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_work_order, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.tvHeader.text = displayList[position] as String
        } else if (holder is ItemViewHolder) {
            val order = displayList[position] as WorkOrder
            holder.bind(order)
        }
    }

    override fun getItemCount() = displayList.size

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHeader: TextView = view.findViewById(R.id.tvHeaderTitle)
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvPriorityValue: TextView = view.findViewById(R.id.tvPriorityValue)
        private val cbCompleted: CheckBox = view.findViewById(R.id.cbCompleted)

        fun bind(order: WorkOrder) {
            // 1. CLEAR listener first
            cbCompleted.setOnCheckedChangeListener(null)

            tvTitle.text = order.title
            tvPriorityValue.text = order.priority

            // 2. SET visual state
            cbCompleted.isChecked = order.isCompleted

            if (order.isCompleted) {
                tvTitle.setTextColor(Color.parseColor("#4CAF50")) // Green for success
            } else {
                // FIXED: Using itemView.context instead of holder
                tvTitle.setTextColor(itemView.context.getColor(android.R.color.tab_indicator_text))
            }

            // Set Priority Color
            tvPriorityValue.setTextColor(when (order.priority.lowercase()) {
                "high", "critical" -> Color.RED
                "medium" -> Color.parseColor("#FF9800") // Orange
                "low" -> Color.parseColor("#FBC02D") // Yellow/Amber
                else -> Color.GRAY
            })

            // 3. ATTACH new listener
            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked != order.isCompleted) {
                    onStatusChanged(order.copy(isCompleted = isChecked))
                }
            }
        }
    }
}