package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.AverageRating

import com.example.somefood.data.model.OrderRating
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderRatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserRating(newRating: OrderRating)

    @Query("UPDATE user_rating SET starForClient = :rating WHERE orderId = :orderId")
    suspend fun increaseRatingByClients(orderId: Int, rating: Double)

    @Query("UPDATE user_rating SET starForCreator = :rating WHERE orderId = :orderId")
    suspend fun increaseRatingByCreator(orderId: Int, rating: Double)

    @Query("SELECT * FROM user_rating WHERE userId = :userId")
    fun observeRatingOrder(userId: Int): Flow<List<OrderRating>>

}