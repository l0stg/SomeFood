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

    fun checkFavorite(foodId: Int, userId: Int): Flow<FavoriteModel> =
        favoriteDao.checkFavorite(foodId = foodId, userId = userId)

    suspend fun updateFavoriteTable(id: Int): List<Int> =
        favoriteDao.updateFavoriteTable(id)

    suspend fun deleteItem(idFood: Int, userID: Int) {
        favoriteDao.deleteItem(idFood, userID)
    }
}