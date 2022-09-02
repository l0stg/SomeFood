package com.example.somefood.ui.detailFood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.model.FoodDataModel
import com.example.somefood.data.model.OrderRating
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailFoodViewModel(
    private val repositoryFavorite: RepositoryFavorite,
    private val repositoryUser: RepositoryUser,
    private val repositoryFood: RepositoryFood,
) : ViewModel() {

    private val _model= MutableStateFlow<FoodDataModel?>(null)
    val model: Flow<FoodDataModel?> = _model

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

    fun getInfo(foodId: Int) {
        viewModelScope.launch {
            _model.value = repositoryFood.getElement(foodId)
        }
    }
}