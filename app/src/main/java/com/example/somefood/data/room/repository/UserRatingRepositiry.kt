package com.example.somefood.data.room.repository

import com.example.somefood.data.model.AverageRating
import com.example.somefood.data.model.UserRating
import com.example.somefood.data.room.dao.UserRatingDao
import kotlinx.coroutines.flow.Flow

class UserRatingRepositiry(
    private val myDao: UserRatingDao
) {

    suspend fun updateUserRating(newUserRating: UserRating) =
        myDao.updateUserRating(newUserRating)

    suspend fun observeUserRating(userID: Int): AverageRating =
        myDao.observeUserRating(userID)

}