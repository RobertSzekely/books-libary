package com.robertszekely.booklibrary.data.network

import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.models.BooksResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookLibraryService {

    @GET("books")
    fun getBooks(): Call<BooksResponse>

    @POST("books")
    fun addBook(@Body book: Book): Call<Void>
}