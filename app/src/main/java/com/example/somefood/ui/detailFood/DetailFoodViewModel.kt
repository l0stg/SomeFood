package com.example.somefood.ui.detailFood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FavoriteModel
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryUser
import kotlinx.coroutines.launch

class DetailFoodViewModel(
    private val repositoryFavorite: RepositoryFavorite,
    private val repositoryUser: RepositoryUser
) : ViewModel() {

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
}