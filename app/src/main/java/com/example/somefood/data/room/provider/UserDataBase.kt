package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.FavoriteConverter
import com.example.somefood.data.room.dao.UserDao

@Database(entities = [UserModel::class], version = 1)
@TypeConverters(FavoriteConverter::class)
abstract class UserDataBase: RoomDatabase() {
        abstract fun somethingDao(): UserDao
    }
