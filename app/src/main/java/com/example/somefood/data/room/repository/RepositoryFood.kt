package com.example.somefood.data.room.repository


import com.example.somefood.R
import com.example.somefood.data.model.FoodDataModel
import com.example.somefood.data.room.dao.FoodDao
import kotlinx.coroutines.flow.Flow

class RepositoryFood(
    private val foodDao: FoodDao
) {
    suspend fun addAllElement(){
        foodDao.addAllElement(PREPOPULATE_DATA)
    }

    suspend fun updateFavoriteTable(listID: List<Int>): List<FoodDataModel> = foodDao.updateFavoriteTable(listID)

    fun updateTable(): Flow<List<FoodDataModel>> = foodDao.updateFoodTable()

    private val PREPOPULATE_DATA = listOf(
        FoodDataModel(id = 1, name = "БОРЩ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataModel(id = 2, name = "КАРТОШКА", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataModel(id = 3, name = "ПЛОВ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataModel(id = 4, name = "ОКРОШКА", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataModel(id = 5, name = "МЯСО", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataModel(id = 6, name = "КУРИЦА", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataModel(id = 7, name = "ЖАРЕННЫЕ ГВОЗДИ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataModel(id = 8, name = "ЖАРЕННЫЙ ПЕТУХ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
    )

}