package com.test.project

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {

    @POST("list")
    suspend fun getApps(@Body request: AppRequest): Response<List<ApplicationList>>
}

data class AppRequest ( val searchQuery: Int)


