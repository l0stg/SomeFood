package com.example.somefood.ui.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Crypto.encode
import com.example.somefood.ui.Event.Event
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val router: Router,
    private val myRepository: RepositoryUser,
) : ViewModel() {

    private val _status = MutableStateFlow<Event<Boolean>?>(null)
    val status = _status

    // Навигация
    private fun routeToProductList() =
        router.newRootScreen(Screens().routeToProductList())


    private fun routeToCreatorList() =
        router.newRootScreen(Screens().routeToCreatorList())


    // Проверка на соответствие в базе данных
    fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            val checkUser = myRepository.checkAuth(email = email, password = encode(password))
            if (checkUser != null) {
                myRepository.saveUserID(checkUser.userUID)
                when (checkUser.types) {
                    UserTypes.USER -> routeToProductList()
                    UserTypes.CREATOR -> routeToCreatorList()
                }
            } else {
                _status.value = Event(true)

            }
        }
    }

}