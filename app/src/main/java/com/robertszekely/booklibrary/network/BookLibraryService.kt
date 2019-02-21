package com.robertszekely.booklibrary.network

import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.models.BooksResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface BookLibraryService {

    @GET("books")
    fun getBooks(): Call<BooksResponse>

    @GET("books/{bookId}")
    fun getBook(@Path("bookId") bookId: String): Call<Book>

    @POST("books")
    fun addBook(@Body book: Book): Call<Void>

    @DELETE("books/{bookId}")
    fun deleteBook(@Path("bookId") bookId: String): Call<Void>

    @PATCH("books/{bookId}")
    fun updateBook(@Path("bookId") bookId: String, @Body book: Book): Call<Void>
}