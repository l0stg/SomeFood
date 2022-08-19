package com.example.somefood.di

import com.example.somefood.data.room.repository.*
import org.koin.dsl.module

// Сингл репозитория для работы с БД пользователей

val repositoryModule = module {

    single { RepositoryUser(get(), get()) }
    single { RepositoryFood(get()) }
    single { RepositoryFavorite(get()) }
    single { OrderRepository(get()) }
    single { UserRatingRepositiry(get()) }
}