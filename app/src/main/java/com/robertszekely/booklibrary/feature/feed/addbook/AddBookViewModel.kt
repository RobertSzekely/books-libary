package com.robertszekely.booklibrary.feature.feed.addbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.storage.BookRepository
import com.robertszekely.booklibrary.util.Event


class AddBookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _snackBarEvent = MutableLiveData<Event<String>>()
    val snackBarEvent: LiveData<Event<String>>
        get() = _snackBarEvent

    fun addBook(validatedBookData: Book) {
        repository.addBook(validatedBookData, object : BookRepository.ApiCallback<Void> {
            override fun onSuccess(result: Void?) {
                _snackBarEvent.value = Event("Book added successfully!")
            }

            override fun onFailure(errorMessage: String) {
                _snackBarEvent.value = Event("Adding book failed!")
            }
        })
    }
}