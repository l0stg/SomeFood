package com.example.somefood.ui.productListClient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.*
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductListClientViewModel(
    private val router: Router,
    private val repositoryFood: RepositoryFood,
    private val repositoryFavorite: RepositoryFavorite,
    private val repositoryUser: RepositoryUser
): ViewModel() {

    init {
        updateFoodTable()
    }

    private val _list = MutableStateFlow<List<FoodDataBaseModel>>(emptyList())
    val list: Flow<List<FoodDataBaseModel>> = _list

    //Навигация
    fun routeToFavorite(){
        router.navigateTo(Screens().routeToFavorite())
    }

    fun routeToDetail(model: ProductListModel){
        router.navigateTo(Screens().routeToDetail(model))
    }

    fun routeToBascet() {
        router.navigateTo(Screens().routeToBascet())
    }
    fun routeToHelloScreen() {
        repositoryUser.saveUserID(-1)
        router.navigateTo(Screens().routeToHelloScreenFragment())
    }

    fun addNewFavoriteItem(idFood: Int){
        var job: Job? = null
        job = viewModelScope.launch {
            repositoryFavorite.addToFavorite(FavoriteModel(userID = repositoryUser.getUserID(), foodId = idFood))
            job?.cancel()
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