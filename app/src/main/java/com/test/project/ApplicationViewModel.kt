package com.test.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApplicationViewModel : ViewModel() {

    private val _apps = MutableLiveData<List<ApplicationList>>()
    val apps: LiveData<List<ApplicationList>> get() = _apps

    private val _searchQuery = MutableLiveData<String>()
    val filteredUsers: LiveData<List<ApplicationList>> = _searchQuery.map { query ->
        (if (query.isNullOrEmpty()) {
            _apps.value
        } else {
            _apps.value?.filter { app ->
                app.name.contains(query, ignoreCase = true)
            }
        })!!
    }

    init {
        fetchApplicationList(378)
        _searchQuery.value = ""
    }

    private fun fetchApplicationList( id : Int) {
        // Dummy data
        viewModelScope.launch {
            val request = AppRequest(id)
            val response = ApliClient.api.getApps(request)
            if (response.isSuccessful) {
                _apps.value = response.body()
            } else {
                // Handle error
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
