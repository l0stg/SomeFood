package com.example.somefood.ui.signIn

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class SignInViewModel(
    private val router: Router,
    private val myRepository: RepositoryUser,
): ViewModel() {

    val status = MutableStateFlow(false)
    private val _userID = MutableStateFlow(0)
    val userID: Flow<Int> = _userID

    // Навигация
    private fun routeToProductList() {
        router.newRootScreen(Screens().routeToProductList())
    }

    private fun routeToCreatorList() {
        router.newRootScreen(Screens().routeToCreatorList())
    }

    // Проверка на соответствие в базе данных
    fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            val checkUser = myRepository.checkAuth(email = email, password = password)
            if (checkUser != null){
                myRepository.saveUserID(checkUser.id)
                when (checkUser.types) {
                    false -> routeToProductList()
                    true -> routeToCreatorList()
                }
            }else {
                status.value = true
            }
        }
    }
}