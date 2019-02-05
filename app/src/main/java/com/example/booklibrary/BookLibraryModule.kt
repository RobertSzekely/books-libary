package com.example.booklibrary

import com.example.booklibrary.data.NetworkManager
import com.google.gson.GsonBuilder
import org.koin.dsl.module.module

val networkingModule = module {
    single { GsonBuilder().create() }
    factory { NetworkManager(get()) }
}