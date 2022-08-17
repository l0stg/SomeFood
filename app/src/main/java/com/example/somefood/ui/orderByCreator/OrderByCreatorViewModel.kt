package com.example.somefood.ui.orderByCreator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.Status
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrderByCreatorViewModel(
    private val orderRepository: OrderRepository,
    private val userRepository: RepositoryUser
) : ViewModel() {

    private val _list = MutableStateFlow<List<Order>>(emptyList())
    val list: Flow<List<Order>> = _list

    init {
        updateInUI()
    }

    fun addInJob(item: Order) {
        val newStatus = when (item.status) {
            Status.WAIT -> Status.JOB
            Status.JOB -> Status.COMPLIT
            Status.COMPLIT -> Status.COMPLIT
        }
        viewModelScope.launch {
            orderRepository.addNewBuy(
                Order(
                    id = item.id,
                    orderName = item.orderName,
                    userID = item.userID,
                    timeToComplit = item.timeToComplit,
                    integerBuy = item.integerBuy,
                    status = newStatus,
                    image = item.image,
                    userIdGoToJob = item.userIdGoToJob
                )
            )
        }
    }

    private fun updateInUI() {
        viewModelScope.launch {
            orderRepository.observeOrderTableByCreator(
                userRepository.getUserID()
            ).collect {
                _list.value = it
            }
        }
    }

}