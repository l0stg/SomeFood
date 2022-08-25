package com.example.somefood.ui.historyOrderFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.OrderRating
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.data.room.repository.UserRatingRepositiry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HistoryOrderViewModel(
    private val orderRatingRepository: UserRatingRepositiry,
    private val userRepository: RepositoryUser,
) : ViewModel() {

    private val _list = MutableStateFlow<List<OrderRating>>(emptyList())
    val list: Flow<List<OrderRating>> = _list

    init {
        observerData()
    }

    private fun observerData(){
        viewModelScope.launch {
            orderRatingRepository.observeRatingOrder(userRepository.getUserID()).collect{
                _list.value = it
            }
        }
    }
}