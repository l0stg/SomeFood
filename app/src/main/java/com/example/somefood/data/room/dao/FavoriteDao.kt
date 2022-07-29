package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteFood(newFavoriteModel: FavoriteModel)

    @Query("SELECT * FROM foodFavorite_table WHERE foodID LIKE :idFood AND userID LIKE :userID ")
    fun checkFavorite(userID: Int, idFood: Int): Flow<FavoriteModel>

    @Query("SELECT foodID FROM foodFavorite_table WHERE userID LIKE :userID")
    fun updateFavoriteTable(userID: Int): Flow<List<Int>>

    @Query("DELETE FROM foodFavorite_table WHERE foodID LIKE :idFood AND userID LIKE :userID ")
    suspend fun deleteItem(idFood: Int, userID: Int)
}