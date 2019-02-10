package com.robertszekely.booklibrary.data.network

import com.robertszekely.booklibrary.data.models.BooksResponse
import retrofit2.Call
import retrofit2.http.GET

interface BookLibraryService {

    @GET("books")
    fun getBooks(): Call<BooksResponse>
}