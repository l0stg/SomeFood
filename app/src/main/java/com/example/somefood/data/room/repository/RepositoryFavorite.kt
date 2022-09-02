package com.example.somefood.data.room.repository

import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.room.dao.FavoriteDao

class RepositoryFavorite(
    private val favoriteDao: FavoriteDao
) {

    suspend fun addToFavorite(newFavoriteModel: FavoriteModel) =
        favoriteDao.addFavoriteFood(newFavoriteModel)

    suspend fun observeFavoriteTable(id: Int): List<Int> =
        favoriteDao.observeFavoriteTable(id)

    suspend fun deleteItem(idFood: Int, userID: Int) =
        favoriteDao.deleteItem(idFood, userID)
}