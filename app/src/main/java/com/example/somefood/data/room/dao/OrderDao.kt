package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.model.UserModel
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(newModel: OrderClass)

    @Query("SELECT * FROM order_table ")
    fun getOrderTable(): Flow<List<OrderClass>>

    @Query("SELECT * FROM order_table WHERE ")
    fun getOrderByClient(userID: Int): Flow<List<OrderClass>>

}