package com.example.exampleretrofitvsokhttp.remote

class ApiUtils {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun getApiService()= RetrofitClient.getClient(BASE_URL)?.create(APIService::class.java)
    }
}
