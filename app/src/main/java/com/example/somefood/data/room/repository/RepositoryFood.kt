package com.example.somefood.data.room.repository


import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.room.dao.FoodDao
import kotlinx.coroutines.flow.Flow

class RepositoryFood(
    private val foodDao: FoodDao
) {
    suspend fun addAllElement(item: List<FoodDataBaseModel>){
        foodDao.addAllElement(item)
    }

    //fun checkFood(email: String, name: String): Flow<List<FavoriteDataBaseModel>> = favoriteDao.checkFood(email, name)

    /*suspend fun deleteItem(item: FavoriteDataBaseModel){
        favoriteDao.deleteElement(item)
    }*/

    fun updateFavoriteTable(listID: List<Int>): Flow<List<FoodDataBaseModel>> = foodDao.updateFavoriteTable(listID)

    fun updateTable(): Flow<List<FoodDataBaseModel>> = foodDao.updateFoodTable()
}