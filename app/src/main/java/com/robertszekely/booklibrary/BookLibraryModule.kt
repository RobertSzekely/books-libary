package com.robertszekely.booklibrary

import com.google.gson.GsonBuilder
import com.robertszekely.booklibrary.data.storage.BookRepository
import com.robertszekely.booklibrary.feature.detail.BookDetailViewModel
import com.robertszekely.booklibrary.feature.feed.BookFeedViewModel
import com.robertszekely.booklibrary.feature.add.AddBookViewModel
import com.robertszekely.booklibrary.network.NetworkManager
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
    viewModel { (bookId: String) -> BookDetailViewModel(bookId, get()) }
}