package com.robertszekely.booklibrary

import com.google.gson.GsonBuilder
import com.robertszekely.booklibrary.data.network.NetworkManager
import com.robertszekely.booklibrary.data.storage.BookRepository
import com.robertszekely.booklibrary.feature.detail.DetailViewModel
import com.robertszekely.booklibrary.feature.feed.BookFeedViewModel
import com.robertszekely.booklibrary.feature.feed.addbook.AddBookViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val networkingModule = module {
    single { GsonBuilder().create() }
    factory { NetworkManager(get()) }
}

val repositoryModule = module {
    single { BookRepository(get()) }
}

val viewModelsModule = module {
    viewModel<BookFeedViewModel>()
    viewModel<AddBookViewModel>()
    viewModel { (bookId: String) -> DetailViewModel(bookId, get()) }
}