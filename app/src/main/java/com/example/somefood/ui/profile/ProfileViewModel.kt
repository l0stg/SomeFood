package com.example.somefood.ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserProfileModel
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val router: Router,
    private val userRepository: RepositoryUser,
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserProfileModel?>(null)
    val userProfile: Flow<UserProfileModel?> = _userProfile

    private fun routeToProductList() =
        router.newRootScreen(Screens().routeToProductList())

    private fun routeToCreatorList() =
        router.newRootScreen(Screens().routeToCreatorList())

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            userRepository.observeUserByIdFlow(userRepository.getUserID()).collect {
                _userProfile.value = it
            }
        }
    }

    private fun switchTypes(types: UserTypes) {
        viewModelScope.launch {
            userRepository.updateUserType(userRepository.getUserID(), types)
        }
    }

    fun updateDescription(newDescription: String) {
        viewModelScope.launch {
            userRepository.updateUserDescription(userRepository.getUserID(), newDescription)
        }
    }

    fun goSwitchType(isChecked: Boolean) {
        if (isChecked) {
            switchTypes(UserTypes.CREATOR)
        } else {
            switchTypes(UserTypes.USER)
        }
    }

    fun routeToMainScreen() {
        viewModelScope.launch {
            when (
                userRepository
                    .observeUserById(
                        userRepository.getUserID()
                    )
                    .types) {
                UserTypes.USER -> routeToProductList()
                UserTypes.CREATOR -> routeToCreatorList()
            }
        }
    }

    private fun setPhotoProfile(profilePhoto: String) {
        viewModelScope.launch {
            userRepository.setPhoto(userRepository.getUserID(), profilePhoto)
        }
    }

    fun savePhoto(newUri: Uri?) {
        viewModelScope.launch {
            setPhotoProfile(userRepository.savePhoto(newUri))
        }
    }

    fun routeToHistoryOrder() {
        router.navigateTo(Screens().routeToHistoryOrder())
    }
}