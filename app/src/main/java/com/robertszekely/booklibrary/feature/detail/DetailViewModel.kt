package com.robertszekely.booklibrary.feature.detail

import androidx.lifecycle.ViewModel;
import com.robertszekely.booklibrary.data.storage.BookRepository

class DetailViewModel(val bookId: String, private val repository: BookRepository) : ViewModel() {


}
