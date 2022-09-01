package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.data.model.*
import com.example.somefood.data.room.dao.*

@Database(
    entities = [
        FoodDataModel::class,
        FavoriteModel::class,
        Order::class,
        UserModel::class,
        OrderRating::class,
               ],
    version = 1
)
abstract class SomeFoodDataBase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
    abstract fun foodDao(): FoodDao
    abstract fun orderDao(): OrderDao
    abstract fun userDao(): UserDao
    abstract fun userRatingDao(): OrderRatingDao
}