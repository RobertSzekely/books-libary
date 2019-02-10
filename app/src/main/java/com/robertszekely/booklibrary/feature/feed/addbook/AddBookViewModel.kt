package com.robertszekely.booklibrary.feature.feed.addbook

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.storage.BookRepository


class AddBookViewModel(private val repository: BookRepository) : ViewModel() {

    val shouldCloseActivity = MutableLiveData<Boolean>()

    fun addBook(validatedBookData: Book) {
        repository.addBook(validatedBookData, object : BookRepository.ApiCallback<Void> {
            override fun onSuccess(result: Void?) {
                shouldCloseActivity.value = true
                Log.d("AddBookViewModel", "Book added successfully!")
            }

            override fun onFailure(errorMessage: String) {
                Log.d("AddBookViewModel", "Adding book failed!")
            }
        })
    }
}