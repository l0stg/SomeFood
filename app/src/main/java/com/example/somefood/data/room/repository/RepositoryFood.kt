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

    fun updateFavoriteTable(listID: List<Int>): Flow<List<FoodDataBaseModel>> = foodDao.updateFavoriteTable(listID)

    fun updateTable(): Flow<List<FoodDataBaseModel>> = foodDao.updateFoodTable()
}