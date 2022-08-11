package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.Order
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(newModel: Order)

    @Query("SELECT * FROM order_table ")
    fun observeOrderTable(): Flow<List<Order>>

    @Query("SELECT * FROM order_table WHERE userID LIKE :userID")
    fun observeOrderTableUser(userID: Int): Flow<List<Order>>

}