package com.test.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ApplicationAdapter : ListAdapter<ApplicationList, ApplicationAdapter.ApplicationViewHolder>(AppDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_application, parent, false)
        return ApplicationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int) {
        val app = getItem(position)
        holder.bind(app)
    }

    class ApplicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val switchView: SwitchCompat = itemView.findViewById(R.id.switchButton)

        fun bind(app: ApplicationList) {
            nameTextView.text = app.name
        }
    }
    class AppDiffCallback : DiffUtil.ItemCallback<ApplicationList>() {
        override fun areItemsTheSame(oldItem: ApplicationList, newItem: ApplicationList): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ApplicationList, newItem: ApplicationList): Boolean {
            return oldItem == newItem
        }
    }
}
