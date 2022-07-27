package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.UserDao

@Database(entities = [UserModel::class], version = 1)
abstract class UserDataBase: RoomDatabase() {

        abstract fun somethingDao(): UserDao
    }
