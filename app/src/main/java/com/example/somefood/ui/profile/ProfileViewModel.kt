package com.example.somefood.ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.*
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.data.room.repository.UserRatingRepositiry
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val router: Router,
    private val userRepository: RepositoryUser,
    private val userRatingRepositiry: UserRatingRepositiry,
) : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfileModel("", UserTypes.USER, "", 0, 0, 0.0, 0.0))
    val userProfile: Flow<UserProfileModel> = _userProfile

    private fun routeToProductList() =
        router.newRootScreen(Screens().routeToProductList())

    private fun routeToCreatorList() =
        router.newRootScreen(Screens().routeToCreatorList())

    init{
        getUserProfile()
    }

    private fun getUserProfile(){
        viewModelScope.launch {
            val userInfo = userRepository.observeUserById(userRepository.getUserID())
            val orderRating = userRatingRepositiry.observeUserRating(userRepository.getUserID())
            _userProfile.value = UserProfileModel(
                eMail = userInfo.eMail,
                types = userInfo.types,
                description = userInfo.description,
                orderByClient = userInfo.orderByClient,
                orderByCreator = userInfo.orderByCreator,
                ratingByClient = orderRating.starForClient,
                ratingByCreator = orderRating.starForCreator,
            )
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
        when (isChecked) {
            true -> switchTypes(UserTypes.CREATOR)
            false -> switchTypes(UserTypes.USER)
        }
    }

    fun routeToMainScreen() {
        viewModelScope.launch {
            when (
                userRepository
                .observeUserById(
                    userRepository.getUserID())
                .types) {
                UserTypes.USER -> routeToProductList()
                UserTypes.CREATOR -> routeToCreatorList()
            }
        }
    }
}