package com.example.somefood.data.room.repository

import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.room.dao.FavoriteDao

class RepositoryFavorite(
    private val favoriteDao: FavoriteDao
) {

    suspend fun getFavoriteTable(id: Int): List<Int> =
        favoriteDao.getFavoriteTable(id)

    suspend fun deleteItem(idFood: Int, userID: Int) =
        favoriteDao.deleteItem(idFood, userID)

    suspend fun addAndDeleteFavorite(newFavoriteModel: FavoriteModel) {
        if (favoriteDao.checkingFavoriteTable(
                newFavoriteModel.foodId,
                newFavoriteModel.userId
            ) == null
        )
            favoriteDao.addFavoriteFood(newFavoriteModel)
        else
            favoriteDao.deleteItem(newFavoriteModel.foodId, newFavoriteModel.userId)
    }
}