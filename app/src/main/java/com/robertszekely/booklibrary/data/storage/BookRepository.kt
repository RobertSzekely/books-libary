package com.robertszekely.booklibrary.data.storage

import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.models.BooksResponse
import com.robertszekely.booklibrary.data.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookRepository(private val networkManager: NetworkManager) {

    fun getBooks(callback: ApiCallback) {
        networkManager.service.getBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onSuccess(it.books)
                    }
                } else {
                    callback.onFailure("Loading books was unsuccessful!")
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                callback.onFailure("Friend request reject failed with ${t.message}")
            }

        })
    }

    interface ApiCallback {
        fun onSuccess(books: List<Book>)

        fun onFailure(errorMessage: String)
    }

}