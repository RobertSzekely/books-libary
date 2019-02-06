package com.example.booklibrary

import android.app.Application
import org.koin.android.ext.android.startKoin

@Suppress("unused")
class BookLibraryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkingModule, repositoryModule, viewModelsModule))
    }
}