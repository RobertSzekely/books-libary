package com.robertszekely.booklibrary.feature.feed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.storage.BookRepository

class BookFeedViewModel(private val repository: BookRepository) : ViewModel() {

    val booksList = MutableLiveData<List<Book>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        loadBooks()
    }

    private fun loadBooks() {
        isLoading.postValue(true)
        repository.getBooks(object : BookRepository.ApiCallback<List<Book>> {
            override fun onSuccess(result: List<Book>?) {
                booksList.postValue(result)
                isLoading.postValue(false)
            }

            override fun onFailure(errorMessage: String) {
                Log.d("BookFeedViewModel", "Loading books failed!")
                isLoading.postValue(false)
            }
        })
    }
}
