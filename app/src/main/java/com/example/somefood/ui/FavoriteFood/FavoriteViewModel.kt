package com.example.somefood.ui.FavoriteFood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repositoryFood:  RepositoryFood,
    private val repositoryFavorite: RepositoryFavorite
): ViewModel() {

    private val _list = MutableStateFlow<List<FoodDataBaseModel>>(emptyList())
    val list: Flow<List<FoodDataBaseModel>> = _list

        // Обновляет данные если список изменился
    fun updateUI(id: Int) {
        viewModelScope.launch {
            repositoryFavorite.updateFavoriteTable(id).collect {
                repositoryFood.updateFavoriteTable(it).collect {
                    _list.value = it
                }
            }
        }
    }

    fun deleteFood(idFood: Int, userID: Int) {
        viewModelScope.launch {
            repositoryFavorite.deleteItem(idFood, userID)
            updateUI(userID)
        }
    }
}
