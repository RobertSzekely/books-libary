package com.example.booklibrary.data

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager(gson: Gson) {

    val service: BookLibraryService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(BookLibraryService::class.java)

    companion object {
        const val BASE_URL = "http://192.168.100.4:3000/"
    }
}