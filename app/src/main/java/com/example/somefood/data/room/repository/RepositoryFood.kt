package com.example.somefood.data.room.repository


import com.example.somefood.R
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.room.dao.FoodDao
import kotlinx.coroutines.flow.Flow

class RepositoryFood(
    private val foodDao: FoodDao
) {
    suspend fun addAllElement(){
        foodDao.addAllElement(PREPOPULATE_DATA)
    }

    fun updateFavoriteTable(listID: List<Int>): Flow<List<FoodDataBaseModel>> = foodDao.updateFavoriteTable(listID)

    fun updateTable(): Flow<List<FoodDataBaseModel>> = foodDao.updateFoodTable()

    private val PREPOPULATE_DATA = listOf(
        FoodDataBaseModel(id = 1, name = "БОРЩ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 2, name = "КАРТОШКА", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 3, name = "ПЛОВ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 4, name = "ОКРОШКА", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 5, name = "МЯСО", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 6, name = "КУРИЦА", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 7, name = "ЖАРЕННЫЕ ГВОЗДИ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 8, name = "ЖАРЕННЫЙ ПЕТУХ", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
    )

}