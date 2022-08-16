package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.FoodDataModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {

    @Query("SELECT * FROM food_table")
    fun updateFoodTable(): Flow<List<FoodDataModel>>

    @Query("SELECT * FROM food_table WHERE id IN (:listID) ")
    suspend fun updateFavoriteTable(listID: List<Int>): List<FoodDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllElement(newFavorite: List<FoodDataModel>)


}