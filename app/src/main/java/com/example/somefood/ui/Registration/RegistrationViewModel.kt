package com.example.somefood.ui.Registration

import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Crypto.encode
import com.example.somefood.ui.Event.Event
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val router: Router,
    private val myRepository: RepositoryUser
) : ViewModel() {


    private val _doubleRegistr = MutableStateFlow<Event<Boolean>?>(null)
    val doubleRegistr = _doubleRegistr

    private fun routeToProductList() =
        router.newRootScreen(Screens().routeToProductList())

    private fun routeToCreatorList() =
        router.newRootScreen(Screens().routeToCreatorList())

    fun addUser(email: String, password: String, types: UserTypes) {
        if (isEmailValid(email) && password.isNotEmpty()) {
            viewModelScope.launch {
                if (myRepository.checkRegistration(email)) {
                    myRepository.addUser(
                        UserModel(
                            eMail = email,
                            password = encode(password),
                            types = types
                        )
                    )
                    when (types) {
                        UserTypes.USER -> routeToProductList()
                        UserTypes.CREATOR -> routeToCreatorList()
                    }
                } else {
                    _doubleRegistr.value = Event(true)
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}