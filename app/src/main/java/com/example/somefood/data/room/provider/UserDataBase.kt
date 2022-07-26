package com.example.somefood.data.room.provider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.Dao

@Database(entities = [UserModel::class], version = 1)
abstract class UserDataBase: RoomDatabase(){

        companion object {
             var instance: UserDataBase? = null

             fun create(context: Context) {
                 instance = Room.databaseBuilder(context, UserDataBase::class.java, "user_table").build()
             }
         }

        abstract fun somethingDao(): Dao
    }
