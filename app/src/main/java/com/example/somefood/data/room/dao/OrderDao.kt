package com.example.somefood.data.room.dao

import androidx.room.*
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.Status
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(newModel: Order): Long

    @Query("SELECT * FROM order_table WHERE status LIKE :status ")
    fun observeOrderTable(status: Status): Flow<List<Order>>

    @Query("SELECT * FROM order_table WHERE userIdGoToJob LIKE :userId ")
    fun observeOrderTableByCreator(userId: Int): Flow<List<Order>>

    @Query("SELECT * FROM order_table WHERE userID LIKE :userID")
    fun observeOrderTableUser(userID: Int): Flow<List<Order>>

    @Delete
    suspend fun deleteOrder(order: Order)

}