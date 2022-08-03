package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.R
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.room.dao.FoodDao

@Database(entities = [FoodDataBaseModel::class], version = 1)
abstract class FoodDataBase: RoomDatabase() {

    abstract fun foodDao(): FoodDao
}