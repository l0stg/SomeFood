package com.example.somefood.data.room.repository

import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.room.dao.FavoriteDao
import kotlinx.coroutines.flow.Flow

class RepositoryFavorite(
    private val favoriteDao: FavoriteDao
) {
    suspend fun addToFavorite(newFavoriteModel: FavoriteModel) {
        favoriteDao.addFavoriteFood(newFavoriteModel)
    }

    fun checkFavorite(idFood: Int, userID: Int): Flow<FavoriteModel> =
        favoriteDao.checkFavorite(idFood = idFood, userID = userID)

    fun updateFavoriteTable(id: Int): Flow<List<Int>> =
        favoriteDao.updateFavoriteTable(id)

    suspend fun deleteItem(idFood: Int, userID: Int) {
        favoriteDao.deleteItem(idFood, userID)
    }
}