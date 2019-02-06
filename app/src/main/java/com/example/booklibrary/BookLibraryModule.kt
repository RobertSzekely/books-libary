package com.example.booklibrary

import com.example.booklibrary.data.network.NetworkManager
import com.example.booklibrary.data.storage.BookRepository
import com.example.booklibrary.feature.feed.BookFeedViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.experimental.builder.viewModel
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
}