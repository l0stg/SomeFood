package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.data.model.FavoriteDataBaseModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.FavoriteDao
import com.example.somefood.data.room.dao.UserDao

@Database(entities = [FavoriteDataBaseModel::class], version = 1)
abstract class FavoriteDataBase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}