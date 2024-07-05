package com.test.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApplicationViewModel : ViewModel() {

    private val _apps = MutableLiveData<List<App>>()
    val apps: LiveData<List<App>> get() = _apps

    private val _searchQuery = MutableLiveData<String>()
    val filteredApps: LiveData<List<App>> = MediatorLiveData<List<App>>().apply {
        addSource(_apps) { value = filterApps(_searchQuery.value, it) }
        addSource(_searchQuery) { value = filterApps(it, _apps.value) }
    }

    init {
        _searchQuery.value = ""
        fetchApps(378)
    }

    private fun filterApps(query: String?, apps: List<App>?): List<App> {
        return if (query.isNullOrEmpty()) {
            apps ?: emptyList()
        } else {
            apps?.filter { app ->
                app.app_name.startsWith(query, ignoreCase = true)
            } ?: emptyList()
        }
    }

    private fun fetchApps(id:Int) {
        viewModelScope.launch {

            val request = AppRequest(id)
            val response =ApliClient.api.getApps("v1/apps/list?kid_id="+id)
            if (response.isSuccessful && response.body()?.success == true) {
                _apps.value = response.body()?.data?.app_list
            } else {
                // Handle error
            }
        }
    }

//    val filteredApps: LiveData<List<App>> = _searchQuery.map { query ->
//        if (query.isNullOrEmpty()) {
//            _apps.value ?: emptyList()
//        } else {
//            _apps.value?.filter { app ->
//                app.app_name.contains(query, ignoreCase = true)
//            } ?: emptyList()
//        }
//    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
