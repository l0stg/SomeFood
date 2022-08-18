package com.example.somefood.ui.orderList

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

class OrderFragmentViewModel(
    private val orderRepository: OrderRepository,
    private val router: Router,
    private val userRepository: RepositoryUser
) : ViewModel() {

    private val _list = MutableStateFlow<List<Order>>(emptyList())
    val list: Flow<List<Order>> = _list

    init {
        updateInUI()
    }

    private fun updateInUI() {
        viewModelScope.launch {
            orderRepository.observeOrderTable(Status.WAIT).collect {
                _list.value = it
            }
        }
    }

    fun addInJob(item: Order) {
        viewModelScope.launch {
            orderRepository.addNewBuy(
                Order(
                    id = item.id,
                    orderName = item.orderName,
                    userID = item.userID,
                    timeToComplit = item.timeToComplit,
                    integerBuy = item.integerBuy,
                    status = Status.JOB,
                    image = item.image,
                    userIdGoToJob = userRepository.getUserID()
                )
            )
        }
    }

    fun routeToHelloScreen() {
        userRepository.saveUserID(-1)
        router.newRootScreen(Screens().routeToHelloScreenFragment())
    }

    fun routeToOrderByCreator() {
        router.navigateTo(Screens().routeToOrderByCreator())
    }

    fun routeToProfile() {
        router.navigateTo( Screens().routeToProfile() )
    }
}