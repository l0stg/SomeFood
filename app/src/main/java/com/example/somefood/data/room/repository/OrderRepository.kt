package com.example.somefood.data.room.repository

import com.example.somefood.data.model.Order
import com.example.somefood.data.room.dao.OrderDao
import kotlinx.coroutines.flow.Flow


class OrderRepository(
    private val myDao: OrderDao,
) {

    suspend fun addNewBuy(newItem: Order) = myDao.addOrder(newItem)

    fun observeOrderTable(): Flow<List<Order>> = myDao.observeOrderTable()

    fun observeOrderTableUser(userID: Int): Flow<List<Order>> = myDao.observeOrderTableUser(userID)

    suspend fun deleteOrder(order: Order) = myDao.deleteOrder(order)

}