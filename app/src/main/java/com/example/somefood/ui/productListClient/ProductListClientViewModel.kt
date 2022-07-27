package com.example.somefood.ui.productListClient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FavoriteDataBaseModel
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.room.dao.FavoriteDao
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductListClientViewModel(
    private val router: Router,
    private val repositoryFavorite: RepositoryFavorite
): ViewModel() {
    val email = "q"
    fun routeToFavorite(){
        router.navigateTo(Screens().routeToFavorite())
    }

    fun routeToDetail(){
        router.navigateTo(Screens().routeToDetail())
    }

    fun addToFavorite(item: ProductListModel) {
        var job: Job? = null
        val itemFavorite = FavoriteDataBaseModel(name = item.name, description = item.description, image = item.image, email = email)
        job = viewModelScope.launch {
            repositoryFavorite.checkFood(itemFavorite.email, itemFavorite.name).collect{
                if (it.isEmpty()) {
                    repositoryFavorite.addFavorite(itemFavorite)
                    job?.cancel()
                }
            }
        }
    }
}