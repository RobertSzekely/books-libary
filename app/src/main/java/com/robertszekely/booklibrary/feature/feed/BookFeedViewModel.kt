package com.robertszekely.booklibrary.feature.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.storage.BookRepository
import com.robertszekely.booklibrary.feature.common.BookNavigationActions
import com.robertszekely.booklibrary.util.Event

class BookFeedViewModel(private val repository: BookRepository) : ViewModel(), BookNavigationActions {

    private val _booksList = MutableLiveData<List<Book>>()
    val booksList: LiveData<List<Book>>
        get() = _booksList

    private val _navigateToBookAction = MutableLiveData<Event<String>>()
    val navigateToBookAction: LiveData<Event<String>>
        get() = _navigateToBookAction

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        loadBooks()
    }

    fun loadBooks() {
        _isLoading.postValue(true)
        repository.getBooks(object : BookRepository.ApiCallback<List<Book>> {
            override fun onSuccess(result: List<Book>?) {
                _booksList.postValue(result)
                _isLoading.postValue(false)
            }

            override fun onFailure(errorMessage: String) {
                Log.d("BookFeedViewModel", "Loading books failed!")
                _isLoading.postValue(false)
            }
        })
    }

    override fun openBookDetails(id: String) {
        _navigateToBookAction.value = Event(id)
    }
}

interface ScheduleEventListener : BookNavigationActions
