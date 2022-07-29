package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.room.dao.OrderDao

@Database(entities = [OrderClass::class], version = 1)
abstract class OrderDataBase: RoomDatabase() {

    abstract fun orderDao(): OrderDao
}