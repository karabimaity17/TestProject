package com.test.project

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter (
    private val appViewModel: ApplicationViewModel,
    private val tabTitles: List<String>
) : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.page_application_list, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        // Bind ViewModel to Fragment or RecyclerView
        holder.bind(appViewModel)
    }

    override fun getItemCount(): Int {
        return tabTitles.size
    }

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(viewModel: ApplicationViewModel) {
            // Setup RecyclerView or Fragment
            val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerView)
            val search_text = itemView.findViewById<AutoCompleteTextView>(R.id.search_text)
            val clear = itemView.findViewById<ImageView>(R.id.clear)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context)
            val appAdapter = ApplicationAdapter()
            recyclerView.adapter = appAdapter

            viewModel.filteredUsers.observeForever { users ->
                appAdapter.submitList(users)
            }

            search_text.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    viewModel.setSearchQuery(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }
}