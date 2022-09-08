package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.FavoriteModel

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteFood(newFavoriteModel: FavoriteModel)

    @Query("SELECT foodId FROM foodFavorite_table WHERE userID LIKE :userID")
    suspend fun getFavoriteTable(userID: Int): List<Int>

    @Query("DELETE FROM foodFavorite_table WHERE foodID LIKE :idFood AND userID LIKE :userID ")
    suspend fun deleteItem(idFood: Int, userID: Int)

    @Query("SELECT * FROM foodFavorite_table WHERE foodId LIKE :idFood AND userId LIKE :userID")
    suspend fun checkingFavoriteTable(idFood: Int, userID: Int): FavoriteModel?
}