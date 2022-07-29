package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.room.dao.FavoriteDao
import com.example.somefood.data.room.dao.FoodDao

@Database(entities = [FavoriteModel::class], version = 1)
abstract class FavoriteDataBase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}