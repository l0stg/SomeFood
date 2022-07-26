package com.example.somefood.ui.signIn

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.Repository
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val router: Router,
    private val myRepository: Repository
): ViewModel() {

    private val _list = MutableStateFlow<List<UserModel>>(emptyList())
    val list: Flow<List<UserModel>> = _list

    fun routeToProductList(){
        router.navigateTo(Screens().routeToProductList())
    }

    fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            myRepository.checkAuth(email = email, password = password).collect{
                if (!it.isNullOrEmpty()){
                    routeToProductList()
                } else println("Пользователь не найден")
            }
        }
    }
}