package com.example.booklibrary.data.network

import com.example.booklibrary.data.models.BooksResponse
import retrofit2.Call
import retrofit2.http.GET

interface BookLibraryService {

    @GET("books")
    fun getBooks(): Call<BooksResponse>
}