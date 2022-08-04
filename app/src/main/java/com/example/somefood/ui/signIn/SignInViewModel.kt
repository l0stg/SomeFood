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
    private val mySharedPreferences: SharedPreferences
): ViewModel() {

    val status = MutableStateFlow(false)
    private val _userID = MutableStateFlow<Int>(0)
    val userID: Flow<Int> = _userID

    // Навигация
    private fun routeToProductList(userID: UserModel){
        router.replaceScreen(Screens().routeToProductList(userID))
    }

    private fun routeToCreatorList(){
        router.replaceScreen(Screens().routeToCreatorList())
    }

    // Проверка на соответствие в базе данных
    fun checkUser(email: String, password: String) {
        var job: Job? = null
        job = viewModelScope.launch {
            myRepository.checkAuth(email = email, password = password).collect{
                try {
                    //mySharedPreferences.edit().putInt("preferences", it.id).apply()
                    when(it.types){
                        false -> routeToProductList(it)
                        true -> routeToCreatorList()
                    }
                    job?.cancel()
                } catch (e: NullPointerException) {
                    status.value = true
                }
            }
        }
    }
}