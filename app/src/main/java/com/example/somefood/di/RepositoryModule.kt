package com.example.somefood.di

import com.example.somefood.data.room.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

// Сингл репозитория для работы с БД пользователей

val repositoryModule = module {

    single { RepositoryUser(get(), get(), androidContext()) }
    single { RepositoryFood(get()) }
    single { RepositoryFavorite(get()) }
    single { OrderRepository(get()) }
    single { UserRatingRepositiry(get()) }
}