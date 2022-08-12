package com.example.somefood.ui.Registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val router: Router,
    private val myRepository: RepositoryUser
): ViewModel() {

    private val _statusRegistration: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val statusRegistration: MutableStateFlow<Boolean> = _statusRegistration

    private fun routeToProductList() =
        router.newRootScreen(Screens().routeToProductList())

    private fun routeToCreatorList() =
        router.newRootScreen(Screens().routeToCreatorList())

    fun addUser(email: String, password: String, types: UserTypes) {
        viewModelScope.launch {
            if (myRepository.checkRegistration(email).isEmpty()) {
                val newUserId = myRepository.addUser(UserModel(eMail = email, password = password, types = types))
                myRepository.saveUserID(newUserId.toInt())
                when (types) {
                    UserTypes.USER -> routeToProductList()
                    UserTypes.CREATOR -> routeToCreatorList()
                }
            } else {
                _statusRegistration.value = true
                _statusRegistration.value = false
            }
        }
    }
}