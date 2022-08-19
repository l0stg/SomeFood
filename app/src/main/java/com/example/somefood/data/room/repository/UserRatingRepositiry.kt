package com.example.somefood.data.room.repository

import com.example.somefood.data.model.AverageRating
import com.example.somefood.data.model.OrderRating
import com.example.somefood.data.room.dao.OrderRatingDao

class UserRatingRepositiry(
    private val myDao: OrderRatingDao
) {

    suspend fun updateUserRating(newOrderRating: OrderRating) =
        myDao.updateUserRating(newOrderRating)

    suspend fun observeUserRating(userID: Int): AverageRating =
        myDao.observeUserRating(userID)

}