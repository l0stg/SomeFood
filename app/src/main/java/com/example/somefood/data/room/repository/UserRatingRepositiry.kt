package com.example.somefood.data.room.repository

import com.example.somefood.data.model.OrderRating
import com.example.somefood.data.room.dao.OrderRatingDao
import kotlinx.coroutines.flow.Flow

class UserRatingRepositiry(
    private val myDao: OrderRatingDao
) {

    suspend fun updateUserRating(newOrderRating: OrderRating) =
        myDao.updateUserRating(newOrderRating)

    suspend fun increaseRatingByClient(orderId: Int, rating: Double) =
        myDao.increaseRatingByClients(orderId, rating)

    suspend fun increaseRatingByCreator(orderId: Int, rating: Double) =
        myDao.increaseRatingByCreator(orderId, rating)

    fun observeRatingOrder(userId: Int): Flow<List<OrderRating>> =
        myDao.observeRatingOrder(userId)

}