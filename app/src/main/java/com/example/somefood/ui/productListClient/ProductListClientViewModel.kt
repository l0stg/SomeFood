package com.example.somefood.ui.productListClient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.model.FoodDataModel
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductListClientViewModel(
    private val router: Router,
    private val repositoryFood: RepositoryFood,
    private val repositoryFavorite: RepositoryFavorite,
    private val repositoryUser: RepositoryUser
) : ViewModel() {

    init {
        updateFoodTable()
    }

    private val _list = MutableStateFlow<List<FoodDataModel>>(emptyList())
    val list: Flow<List<FoodDataModel>> = _list

    fun routeToFavorite() =
        router.navigateTo(Screens().routeToFavorite())

    fun routeToDetail(model: ProductListModel) =
        router.navigateTo(Screens().routeToDetail(model))


    fun routeToBascet() =
        router.navigateTo(Screens().routeToBascet())

    fun routeToHelloScreen() {
        repositoryUser.saveUserID(-1)
        router.newRootScreen(Screens().routeToHelloScreenFragment())
    }

    fun addNewFavoriteItem(idFood: Int) {
        viewModelScope.launch {
            repositoryFavorite.addToFavorite(
                FavoriteModel(
                    userId = repositoryUser.getUserID(),
                    foodId = idFood
                )
            )
        }
    }

    // Слежка за данными в таблице
    private fun updateFoodTable() {
        viewModelScope.launch {
            repositoryFood.updateTable().collect {
                _list.value = it
            }
        }
    }
}