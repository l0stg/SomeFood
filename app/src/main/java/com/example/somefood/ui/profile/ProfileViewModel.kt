package com.example.somefood.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val router: Router,
    private val userRepository: RepositoryUser
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserModel>(UserModel(0, "","", UserTypes.USER,))
    val userProfile: Flow<UserModel> = _userProfile

    private fun routeToProductList() =
        router.newRootScreen(Screens().routeToProductList())

    private fun routeToCreatorList() =
        router.newRootScreen(Screens().routeToCreatorList())

    init{
        getUserProfile()
    }

    private fun getUserProfile(){
        viewModelScope.launch {
            _userProfile.value = userRepository.observeUserById(userRepository.getUserID())
        }
    }

    fun switchTypes(types: UserTypes) {
        viewModelScope.launch {
            userRepository.updateUserType(userRepository.getUserID(), types)
        }
    }


    fun routeToMainScreen() {
        viewModelScope.launch {
            val checkUser = userRepository.observeUserById(userRepository.getUserID())
            when (checkUser.types) {
                UserTypes.USER -> routeToProductList()
                UserTypes.CREATOR -> routeToCreatorList()
            }
        }
    }
}