package com.example.somefood.ui.productListClient

import androidx.lifecycle.ViewModel
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router

class ProductListClientViewModel(
    private val router: Router
): ViewModel() {

    fun routeToFavorite(){
        router.navigateTo(Screens().routeToFavorite())
    }

    fun routeToDetail(){
        router.navigateTo(Screens().routeToDetail())
    }

}