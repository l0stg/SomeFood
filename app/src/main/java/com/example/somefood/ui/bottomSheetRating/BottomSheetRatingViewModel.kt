package com.example.somefood.ui.bottomSheetRating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.UserRating
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.data.room.repository.UserRatingRepositiry
import kotlinx.coroutines.launch

class BottomSheetRatingViewModel(
    private val userRatingRepositiry: UserRatingRepositiry,
    private val userRepository: RepositoryUser
) : ViewModel() {

    private fun updateUserRating(newUserRating: UserRating){
        viewModelScope.launch {
            userRatingRepositiry.updateUserRating(newUserRating)
        }
    }

    fun goRatingUser(userIdToRating: Int?, rating: Double){
        viewModelScope.launch {
            val user = userRepository.observeUserById(userIdToRating!!)
            when (user.types){
                UserTypes.USER -> updateUserRating(UserRating(userId = userIdToRating, starForCreator = rating, starForClient = null))
                UserTypes.CREATOR -> updateUserRating(UserRating(userId = userIdToRating, starForCreator = null, starForClient = rating))
            }
        }
    }
}