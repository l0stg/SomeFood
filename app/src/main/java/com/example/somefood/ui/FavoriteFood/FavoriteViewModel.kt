package com.example.somefood.ui.FavoriteFood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FavoriteDataBaseModel
import com.example.somefood.data.room.repository.RepositoryFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repositoryFavorite:  RepositoryFavorite
): ViewModel() {

    private val _list = MutableStateFlow<List<FavoriteDataBaseModel>>(emptyList())
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

    fun deleteFood(item: FavoriteDataBaseModel) {
        viewModelScope.launch {
            repositoryFavorite.deleteItem(item)
        }
    }
}