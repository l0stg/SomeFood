package com.example.somefood.ui.orderBasket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrderBasketViewModel(
    private val orderRepository: OrderRepository,
    private val repositoryUser: RepositoryUser,
): ViewModel() {


    private val _list = MutableStateFlow<List<OrderClass>>(emptyList())
    val list: Flow<List<OrderClass>> = _list

    fun checkOrderByClient(){
        viewModelScope.launch {
            orderRepository.checkOrderByClient(repositoryUser.getUserID()).collect{
                _list.value = it
            }
        }
    }

}