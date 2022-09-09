package com.example.somefood.ui.orderByCreator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.Status
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrderByCreatorViewModel(
    private val orderRepository: OrderRepository,
    private val userRepository: RepositoryUser,
    private val router: Router,
) : ViewModel() {

    private val _list = MutableStateFlow<List<Order>>(emptyList())
    val list: Flow<List<Order>> = _list

    init {
        updateInUI()
    }

    fun addInJob(item: Order) {
        val newStatus = when (item.status) {
            Status.WAIT -> Status.JOB
            Status.JOB -> Status.COMPLETE
            Status.COMPLETE -> {
                Status.COMPLETE
            }
        }
        viewModelScope.launch {
            orderRepository.addNewBuy(
                Order(
                    id = item.id,
                    orderName = item.orderName,
                    userID = item.userID,
                    timeToCompleteHour = item.timeToCompleteHour,
                    timeToCompleteMinutes = item.timeToCompleteMinutes,
                    integerBuy = item.integerBuy,
                    status = newStatus,
                    image = item.image,
                    userIdGoToJob = item.userIdGoToJob,
                    foodId = item.foodId
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

    fun routeToDetailInfo(foodId: Int) {
        router.navigateTo(Screens().routeToDetail(foodId))
    }

}