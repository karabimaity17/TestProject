package com.test.project

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {


    @POST()
    suspend fun getApps(@Url url:String): Response<ApiResponse>
}

data class AppRequest ( val searchQuery: Int)


