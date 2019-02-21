package com.robertszekely.booklibrary.feature.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.data.storage.BookRepository

class BookDetailViewModel(private val bookId: String, private val repository: BookRepository) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _author = MutableLiveData<String>()
    val author: LiveData<String>
        get() = _author

    private val _cover = MutableLiveData<String>()
    val cover: LiveData<String>
        get() = _cover

    init {
        getBookDetails(bookId)
    }

    private fun getBookDetails(bookId: String) {
        repository.getBook(bookId, object : BookRepository.ApiCallback<Book> {
            override fun onSuccess(result: Book?) {
                result?.let {
                    _title.value = it.title
                    _author.value = it.author
                    _cover.value = it.cover
                }
                Log.d(BookDetailViewModel::class.java.name, "Got book details!")
            }

            override fun onFailure(errorMessage: String) {
                Log.d(BookDetailViewModel::class.java.name, "Failed getting book details!")
            }
        })
    }

    fun deleteBook() {
        repository.deleteBook(bookId, object : BookRepository.ApiCallback<Void> {
            override fun onSuccess(result: Void?) {
            }

            override fun onFailure(errorMessage: String) {
            }
        })
    }
}
