package com.example.somefood.ui.FavoriteFood

import androidx.lifecycle.ViewModel
import com.example.somefood.data.room.repository.RepositoryFood

class FavoriteViewModel(
    private val repositoryFavorite:  RepositoryFood
): ViewModel() {

   /* private val _list = MutableStateFlow<List<FavoriteDataBaseModel>>(emptyList())
    val list: Flow<List<FavoriteDataBaseModel>> = _list

    val email = "q"

    init {
        // Наблюдатель за изменением базы данных
        observeAllSomething()
    }

    // Наблюдатель за изменением базы данных
    private fun observeAllSomething(){
        viewModelScope.launch {
            repositoryFavorite.updateTable(email).collect{
                _list.value = it
            }
        }
    }

   *//* fun deleteFood(item: FavoriteDataBaseModel) {
        viewModelScope.launch {
            repositoryFavorite.deleteItem(item)
        }
    }*/
}