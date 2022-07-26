package com.example.somefood.ui.mainActivite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.room.Repository
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class MainViewModel(
    private val router: Router,
    private val myRepository: Repository
): ViewModel() {
    fun create(){
        router.newRootScreen(Screens().routeToHelloScreenFragment())

        viewModelScope.launch {
            myRepository.addUser()
        }
    }
}