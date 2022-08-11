package com.example.somefood.ui.FavoriteFood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repositoryFood:  RepositoryFood,
    private val repositoryFavorite: RepositoryFavorite,
    private val router: Router,
    private val repositoryUser: RepositoryUser
): ViewModel() {

    private val _list = MutableStateFlow<List<FoodDataBaseModel>>(emptyList())
    val list: Flow<List<FoodDataBaseModel>> = _list

    // Обновляет данные если список изменился
    fun updateUI() {
        viewModelScope.launch {
            _list.value = repositoryFood.updateFavoriteTable(
                repositoryFavorite
                    .updateFavoriteTable(
                        repositoryUser.getUserID())
            )
        }
    }

    fun deleteFood(idFood: Int) {
        viewModelScope.launch {
            repositoryFavorite.deleteItem(idFood, repositoryUser.getUserID())
            updateUI()
        }
    }

    fun routeToFavorite(item: ProductListModel) {
        router.navigateTo(Screens().routeToDetail(item))
    }
}
