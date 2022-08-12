package com.example.somefood.ui.mainActivite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class MainViewModel(
    private val router: Router,
    private val myRepository: RepositoryFood,
    private val repositoryUser: RepositoryUser,
): ViewModel() {

    fun create() {
        viewModelScope.launch {
            myRepository.addAllElement()
        }
        checkSessionByUser()
    }

    private fun checkSessionByUser() {
        if (repositoryUser.getUserID() == -1) {
            router.newRootScreen(Screens().routeToHelloScreenFragment())
        } else {
            routerByType(repositoryUser.getUserID())
        }
    }

    private fun routerByType(userID: Int) {
        viewModelScope.launch {
            val user = repositoryUser.observeUserById(userID)
            when (user.types) {
                UserTypes.USER -> router.newRootScreen(Screens().routeToProductList())
                UserTypes.CREATOR -> router.newRootScreen(Screens().routeToCreatorList())
            }
        }
    }
}
