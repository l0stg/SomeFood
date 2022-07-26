package com.example.somefood.ui.signIn

import androidx.lifecycle.ViewModel
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router

class SignInViewModel(
    private val router: Router
): ViewModel() {

    fun routeToProductList(){
        router.navigateTo(Screens().routeToProductList())
    }
}