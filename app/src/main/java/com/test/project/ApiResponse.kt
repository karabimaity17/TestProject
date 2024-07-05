package com.test.project

data class ApiResponse(
    val success: Boolean,
    val data: Data,
    val message: String
)

data class Data(
    val app_list: List<App>
)

data class App(
    val app_id: Int,
    val fk_kid_id: Int,
    val kid_profile_image: String,
    val app_name: String,
    val app_icon: String,
    val app_package_name: String,
    val status: String
)
