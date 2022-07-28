package com.example.somefood.ui.signIn

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

class SignInViewModel(
    private val router: Router,
    private val myRepository: RepositoryUser
): ViewModel() {

    val status = MutableStateFlow(false)
    private val _userID = MutableStateFlow<Int>(0)
    val userID: Flow<Int> = _userID

    // Навигация
    private fun routeToProductList(userID: UserModel){
        router.replaceScreen(Screens().routeToProductList(userID))
    }
    // Проверка на соответствие в базе данных
    fun checkUser(email: String, password: String) {
        var job: Job? = null
        job = viewModelScope.launch {
            myRepository.checkAuth(email = email, password = password).collect{
                if (it != null){
                    routeToProductList(it)
                    job?.cancel()
                } else status.value = true
            }
        }
    }
}