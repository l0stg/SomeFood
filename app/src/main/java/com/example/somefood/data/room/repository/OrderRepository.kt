package com.example.somefood.data.room.repository

import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.room.dao.OrderDao
import kotlinx.coroutines.flow.Flow


class OrderRepository(
    private val myDao: OrderDao,
) {

    suspend fun addNewBuy(newItem: OrderClass){
        myDao.addOrder(newItem)
    }

    fun updateInUI(): Flow<List<OrderClass>> = myDao.getOrderTable()

    fun checkOrderByClient(userID: Int): Flow<List<OrderClass>> = myDao.getOrderByClient(userID)

}