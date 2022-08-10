package com.example.somefood.data.room.repository

import com.example.somefood.data.model.Order
import com.example.somefood.data.room.dao.OrderDao
import kotlinx.coroutines.flow.Flow


class OrderRepository(
    private val myDao: OrderDao,
) {

    suspend fun addNewBuy(newItem: Order) = myDao.addOrder(newItem)

    fun updateInUI(): Flow<List<Order>> = myDao.getOrderTable()

    fun checkOrderByClient(userID: Int): Flow<List<Order>> = myDao.getOrderByClient(userID)

}