package com.example.somefood.ui.productListClient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductListClientViewModel(
    private val router: Router,
    private val repositoryFood: RepositoryFood,
    private val repositoryFavorite: RepositoryFavorite
): ViewModel() {

    init {
        updateFoodTable()
    }
    private val _list = MutableStateFlow<List<FoodDataBaseModel>>(emptyList())
    val list: Flow<List<FoodDataBaseModel>> = _list

    //Навигация
    fun routeToFavorite(userID: Int?){
        router.navigateTo(Screens().routeToFavorite(userID))
    }

    fun routeToDetail(model: ProductListModel){
        router.navigateTo(Screens().routeToDetail(model))
    }
    // добавление элементов
    fun addToFood(newList: List<FoodDataBaseModel>){
        viewModelScope.launch {
            repositoryFood.addAllElement(newList)
        }
    }

    fun updateFavorite(userID: Int, idFood: Int){
        var job: Job? = null
       job = viewModelScope.launch {
            repositoryFavorite.checkFavorite(idFood = idFood, userID = userID).collect {
               if (it == null) {
                   val newFavorite = FavoriteModel(userID = userID, foodId = idFood)
                   repositoryFavorite.addToFavorite(newFavorite)
               }else{
                   repositoryFavorite.deleteItem(idFood, userID)
               }
                job?.cancel()
           }
        }
    }

    // Слежка за данными в таблице
    private fun updateFoodTable(){
        viewModelScope.launch {
            repositoryFood.updateTable().collect {
                _list.value = it
            }
        }
    }
}