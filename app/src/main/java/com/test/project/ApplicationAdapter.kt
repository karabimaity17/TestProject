package com.test.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class ApplicationAdapter : ListAdapter<App, ApplicationAdapter.ApplicationViewHolder>(AppDiffCallback()) {

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
        private val icon_image: ImageView = itemView.findViewById(R.id.icon_image)

        fun bind(app: App) {
            nameTextView.text = app.app_name


            Picasso.get().load(app.app_icon).fit()
                .placeholder(R.drawable.loading)
                .error(R.drawable.photo)
                .into(icon_image);
            switchView.isChecked = app.status.toLowerCase() == "active"
        }
    }
    class AppDiffCallback : DiffUtil.ItemCallback<App>() {
        override fun areItemsTheSame(oldItem: App, newItem: App): Boolean {
            return oldItem.app_name == newItem.app_name
        }

        override fun areContentsTheSame(oldItem: App, newItem: App): Boolean {
            return oldItem == newItem
        }
    }
}
