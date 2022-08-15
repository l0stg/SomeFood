package com.example.somefood.di

import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import org.koin.dsl.module

// Сингл репозитория для работы с БД пользователей

val repositoryModule = module {

    single { RepositoryUser(get(), get()) }
    single { RepositoryFood(get()) }
    single { RepositoryFavorite(get()) }
    single { OrderRepository(get()) }

}