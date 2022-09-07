package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.FoodDataModel
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.model.UserProfileModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {

    @Query("SELECT * FROM food_table")
    fun observeFoodTable(): Flow<List<FoodDataModel>>

    @Query("SELECT * FROM food_table WHERE id IN (:listID) ")
    suspend fun updateFavoriteTable(listID: List<Int>): List<FoodDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllElement(newFavorite: List<FoodDataModel>)

    @Query("SELECT id, name, image, recept, idFavorite"  +
            " FROM food_table LEFT JOIN foodFavorite_table ON id == foodId AND foodFavorite_table.userId LIKE :userId WHERE id LIKE :foodId")
    fun getElement(foodId: Int, userId: Int): Flow<ProductListModel>

    @Query("SELECT id, name, image, recept, idFavorite"  +
            " FROM food_table LEFT JOIN foodFavorite_table ON id == foodId AND foodFavorite_table.userId LIKE :userId ")
    fun observeFoodTableWithFavorite(userId: Int): Flow<List<ProductListModel>>
}