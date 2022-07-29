package com.example.somefood.data.room.dao

import androidx.room.*
import com.example.somefood.data.model.FoodDataBaseModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {

    @Query("SELECT * FROM food_table1")
    fun updateFoodTable(): Flow<List<FoodDataBaseModel>>

    @Query("SELECT * FROM food_table1 WHERE id IN (:listID) ")
    fun updateFavoriteTable(listID: List<Int>): Flow<List<FoodDataBaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllElement(newFavorite: List<FoodDataBaseModel>)


}