package com.example.somefood.ui.helloScreen

import androidx.lifecycle.ViewModel
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router

class HelloScreenViewModel(
    private val router: Router
): ViewModel() {

    fun openSigIn(){
        router.navigateTo(Screens().openSignIn())
    }

    fun openRegistration(){
        router.navigateTo(Screens().openRegistration())
    }
}