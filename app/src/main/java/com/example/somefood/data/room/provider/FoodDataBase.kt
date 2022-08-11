package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.R
import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.FavoriteDao
import com.example.somefood.data.room.dao.FoodDao
import com.example.somefood.data.room.dao.OrderDao
import com.example.somefood.data.room.dao.UserDao

@Database(
    entities = [
        FoodDataBaseModel::class,
        FavoriteModel::class,
        Order::class,
        UserModel::class],
    version = 1)
abstract class SomeFoodDataBase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
    abstract fun foodDao(): FoodDao
    abstract fun orderDao(): OrderDao
    abstract fun somethingDao(): UserDao
}