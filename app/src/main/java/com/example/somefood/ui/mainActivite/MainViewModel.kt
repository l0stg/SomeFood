package com.example.somefood.ui.mainActivite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.R
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class MainViewModel(
    private val router: Router,
    private val myRepository: RepositoryFood
): ViewModel() {

    fun create(){
        viewModelScope.launch {
            myRepository.addAllElement(PREPOPULATE_DATA)
        }
        router.newRootScreen(Screens().routeToHelloScreenFragment())
    }

    // Начальные данные для БД
    private val PREPOPULATE_DATA = listOf(
        FoodDataBaseModel(id = 1, name = "1", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 2, name = "2", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 3, name = "3", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 4, name = "4", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 5, name = "5", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 6, name = "6", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 7, name = "7", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням"),
        FoodDataBaseModel(id = 8, name = "8", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
    )


}