package com.example.somefood.ui.mainActivite

import androidx.lifecycle.ViewModel
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router
): ViewModel() {
    fun create(){
        router.newRootScreen(Screens().routeToHelloScreenFragment())
    }
}