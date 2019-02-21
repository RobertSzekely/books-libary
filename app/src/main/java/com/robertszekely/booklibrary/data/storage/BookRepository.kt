package com.robertszekely.booklibrary.data.storage

import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.models.BooksResponse
import com.robertszekely.booklibrary.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookRepository(private val networkManager: NetworkManager) {

    fun getBooks(callback: ApiCallback<List<Book>>) {
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
                callback.onFailure("Book GET failed with ${t.message}")
            }

        })
    }

    fun getBook(bookId: String, callback: ApiCallback<Book>) {
        networkManager.service.getBook(bookId).enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                } else {
                    callback.onFailure("Loading book details was unsuccessful!")
                }
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                callback.onFailure("Book detail GET failed with ${t.message}")
            }
        })
    }

    fun addBook(book: Book, callback: ApiCallback<Void>) {
        networkManager.service.addBook(book).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback.onSuccess(null)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onFailure("Book POST failed with ${t.message}")
            }
        })
    }

    fun deleteBook(bookId: String, callback: ApiCallback<Void>) {
        networkManager.service.deleteBook(bookId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback.onSuccess(null)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onFailure("Book DELETE failed with ${t.message}")
            }
        })
    }

    interface ApiCallback<T> {
        fun onSuccess(result: T?)

        fun onFailure(errorMessage: String)
    }


}