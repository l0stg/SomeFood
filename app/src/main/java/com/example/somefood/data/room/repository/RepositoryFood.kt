package com.example.somefood.data.room.repository


import com.example.somefood.data.model.FoodDataModel
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.room.dao.FoodDao
import kotlinx.coroutines.flow.Flow

class RepositoryFood(
    private val foodDao: FoodDao
) {
    suspend fun addAllElement() {
        foodDao.addAllElement(PREPOPULATE_DATA)
    }

    suspend fun getElement(foodId: Int, userId: Int): Flow<ProductListModel> =
        foodDao.getElement(foodId, userId)

    suspend fun updateFavoriteTable(listID: List<Int>): List<FoodDataModel> =
        foodDao.updateFavoriteTable(listID)

    fun observeTableWithFavorite(userId: Int): Flow<List<ProductListModel>> =
        foodDao.observeFoodTableWithFavorite(userId)

    private val recept = "Сварить бульон из говядины на косточке.\n" +
            "Свеклу очистить, нарезать соломкой, посолить и сбрызнуть лимонным соком или лимонной кислотой, положить в кастрюлю. Затем добавить измельченный репчатый лук, растертый со свиным салом, томатную пасту, сахар и тушить массу до полу готовности.\n" +
            "Очистить корень петрушки и морковь, нарезать их соломкой и пассировать на сливочном масле.\n" +
            "Очистить картофель, нарезать дольками, положить в процеженный мясной бульон и довести до кипения. Добавить нашинкованную капусту и варить в течение 15 минут.\n" +
            "После этого положить в кастрюлю тушеную свеклу, пассированную петрушку и морковь, нарезанные свежие помидоры, перец, лавровый лист, 1 чайную ложку пассированной муки и варить борщ еще 10 минут.\n" +
            "Заправить нарезанной зеленью и толченым чесноком. Подавать с кусочками мяса, сметаной и зеленью."

    private val foodImg = "https://images.spoonacular.com/file/wximages/316774-312x231.png"
    private val PREPOPULATE_DATA = listOf(
        FoodDataModel(id = 0, name = "БОРЩ", image = foodImg, recept = recept),
        FoodDataModel(id = 1, name = "КАРТОШКА", image = foodImg, recept = recept),
        FoodDataModel(id = 2, name = "ПЛОВ", image = foodImg, recept = recept),
        FoodDataModel(id = 3, name = "ОКРОШКА", image = foodImg, recept = recept),
        FoodDataModel(id = 4, name = "МЯСО", image = foodImg, recept = recept),
        FoodDataModel(id = 5, name = "КУРИЦА", image = foodImg, recept = recept),
        FoodDataModel(id = 6, name = "ЖАРЕННЫЕ ГВОЗДИ", image = foodImg, recept = recept),
        FoodDataModel(id = 7, name = "ЖАРЕННЫЙ ПЕТУХ", image = foodImg, recept = recept),
        FoodDataModel(id = 7, name = "ЖАРЕННЫЙ ПЕТУХ", image = foodImg, recept = recept)
    )


}