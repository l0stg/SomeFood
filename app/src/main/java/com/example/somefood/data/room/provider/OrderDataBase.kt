package com.example.somefood.data.room.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.somefood.data.model.Order
import com.example.somefood.data.room.dao.OrderDao

@Database(entities = [Order::class], version = 1)
abstract class OrderDataBase: RoomDatabase() {

    abstract fun orderDao(): OrderDao
}