package com.example.somefood.ui.FavoriteFood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repositoryFood:  RepositoryFood,
    private val repositoryUser: RepositoryUser
): ViewModel() {

    private val _list = MutableStateFlow<List<FoodDataBaseModel>>(emptyList())
    val list: Flow<List<FoodDataBaseModel>> = _list


    // Тягает у нас актуальный список избранных айди
    fun getFavoriteID(user: Int) {
        viewModelScope.launch {
            repositoryUser.updateUser(user).collect {
                it.favorite?.let { it1 -> updateUI(it1) }
            }
        }
    }

    private fun updateUI(id: List<Int>){
       viewModelScope.launch {
           repositoryFood.updateFavoriteTable(id).collect{
               _list.value = it
           }
        }
    }

    fun deleteFood(id: Int?, userID: Int){
        var job: Job? = null
        job = viewModelScope.launch {
            repositoryUser.updateUser(userID).collect{
                var list: MutableList<Int> = mutableListOf()
                if(it.favorite != null) {
                    list = it.favorite!!.toMutableList()
                    list.remove(id)
                }
                it.favorite = list
                repositoryUser.addUser(it)
                job?.cancel()
            }
        }
    }
}