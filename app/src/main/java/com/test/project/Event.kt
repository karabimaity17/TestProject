package com.test.project

sealed class Event<out T> {
    data class ShowToast(val message: String) : Event<String>()
    // Add more event types as needed (e.g., ShowSnackbar, ShowDialog)
}