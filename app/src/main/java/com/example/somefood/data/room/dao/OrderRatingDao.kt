package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.AverageRating

import com.example.somefood.data.model.OrderRating

@Dao
interface OrderRatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserRating(newRating: OrderRating)

    @Query("SELECT AVG(starForCreator) as starForCreator , AVG(starForClient) as starForClient FROM user_rating WHERE userid LIKE :userId")
    suspend fun observeUserRating(userId: Int): AverageRating
}