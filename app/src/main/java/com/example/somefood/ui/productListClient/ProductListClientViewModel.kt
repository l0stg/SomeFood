package com.example.somefood.ui.productListClient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.model.UserModel
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
    private val repositoryUser: RepositoryUser
): ViewModel() {

    init {
        updateFoodTable()
    }
    private val _list = MutableStateFlow<List<FoodDataBaseModel>>(emptyList())
    val list: Flow<List<FoodDataBaseModel>> = _list

    fun routeToFavorite(userID: Int?){
        router.navigateTo(Screens().routeToFavorite(userID))
    }

    fun routeToDetail(model: ProductListModel){
        router.navigateTo(Screens().routeToDetail(model))
    }

    fun addToFood(newList: List<FoodDataBaseModel>){
        viewModelScope.launch {
            repositoryFood.addAllElement(newList)
        }
    }

    fun updateFavoriteInUser(userID: UserModel, idFood: Int){

        var job: Job? = null
        job = viewModelScope.launch {
            repositoryUser.updateUser(userID.id!!).collect{
                val list: MutableList<Int>
                if(it.favorite != null) {
                    list = it.favorite!!.toMutableList()
                    list.add(idFood)
                }
                else list = mutableListOf(idFood)
                it.favorite = list
                repositoryUser.addUser(it)
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