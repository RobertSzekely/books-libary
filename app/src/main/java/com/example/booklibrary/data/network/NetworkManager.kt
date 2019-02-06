package com.example.booklibrary.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager(gson: Gson) {

    val service: BookLibraryService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().addInterceptor { it.proceed(it.request().newBuilder().addHeader("Authorization", TOKEN).build()) }.build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(BookLibraryService::class.java)

    companion object {
        const val BASE_URL = "http://192.168.100.4:3000"
        const val TOKEN =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAdGVzdC5jb20iLCJ1c2VySWQiOiI1YzU3MjliMmY2ZGE0MzA4Y2VkNDg5MTEiLCJpYXQiOjE1NDkzOTYxODMsImV4cCI6MTU0OTQ4MjU4M30.zfdYNYKhYjVmOtvld8e16I8Y5cPjgMx6q-Clz7hw69U"
    }
}