package com.example.somefood.data.room.repository

import com.example.somefood.data.model.FavoriteDataBaseModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.FavoriteDao
import kotlinx.coroutines.flow.Flow

class RepositoryFavorite(
    private val favoriteDao: FavoriteDao
) {
    suspend fun addFavorite(item: FavoriteDataBaseModel){
        favoriteDao.addElement(item)
    }

    fun checkFood(email: String, name: String): Flow<List<FavoriteDataBaseModel>> = favoriteDao.checkFood(email, name)

    suspend fun deleteItem(item: FavoriteDataBaseModel){
        favoriteDao.deleteElement(item)
    }

    fun updateTable(email: String): Flow<List<FavoriteDataBaseModel>> = favoriteDao.updateFavoriteTable(email)
}