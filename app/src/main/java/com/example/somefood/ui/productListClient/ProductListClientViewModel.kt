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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductListClientViewModel(
    private val router: Router,
    private val repositoryFood: RepositoryFood,
    private val repositoryUser: RepositoryUser
): ViewModel() {
    val email = "q"

    init {
        updateFoodTable()
    }
    private val _list = MutableStateFlow<List<FoodDataBaseModel>>(emptyList())
    val list: Flow<List<FoodDataBaseModel>> = _list

    fun routeToFavorite(){
       // router.navigateTo(Screens().routeToFavorite())
    }

    fun routeToDetail(){
        router.navigateTo(Screens().routeToDetail())
    }

    fun addToFood(newList: List<FoodDataBaseModel>){
        viewModelScope.launch {
            repositoryFood.addAllElement(newList)
        }
    }

    fun updateFavoriteInUser(userID: UserModel, idFood: Int){
        val newList: MutableList<Int> = mutableListOf()
        newList?.add(idFood)
        val newUser = UserModel(id = userID.id, eMail = userID.eMail, password = userID.password, favorite = newList.toList())
        viewModelScope.launch {
            repositoryUser.addUser(newUser)
        }
    }

    private fun updateFoodTable(){
        viewModelScope.launch {
            repositoryFood.updateTable().collect {
                _list.value = it
            }
        }
    }
}