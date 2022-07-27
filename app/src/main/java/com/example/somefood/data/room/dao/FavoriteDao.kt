package com.example.somefood.data.room.dao

import androidx.room.*
import com.example.somefood.data.model.FavoriteDataBaseModel
import com.example.somefood.data.model.UserModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {


    @Query("SELECT * FROM favorite_table")
    fun updateFavoriteTable(): Flow<List<FavoriteDataBaseModel>>

    @Delete
    suspend fun deleteElement(deleteFavorite: FavoriteDataBaseModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addElement(newFavorite: FavoriteDataBaseModel)


}