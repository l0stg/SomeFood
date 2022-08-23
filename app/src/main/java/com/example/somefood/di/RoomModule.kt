package com.example.somefood.di

import androidx.room.Room
import com.example.somefood.data.room.provider.SomeFoodDataBase
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(get(), SomeFoodDataBase::class.java, "food_db").build() }
    single { get<SomeFoodDataBase>().foodDao() }
    single { get<SomeFoodDataBase>().userDao() }
    single { get<SomeFoodDataBase>().orderDao() }
    single { get<SomeFoodDataBase>().favoriteDao() }
    single { get<SomeFoodDataBase>().userRatingDao() }
}