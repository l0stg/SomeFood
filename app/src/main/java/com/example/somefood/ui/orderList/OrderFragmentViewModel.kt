package com.example.somefood.ui.orderList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.room.repository.OrderRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OrderFragmentViewModel(
    private val orderRepository: OrderRepository,
    private val router: Router
): ViewModel() {

    private val _list = MutableStateFlow<List<OrderClass>>(emptyList())
    val list: Flow<List<OrderClass>> = _list


    init {
        updateInUI()
    }

    private fun updateInUI(){
        viewModelScope.launch {
            orderRepository.updateInUI().collect{
                _list.value = it
            }
        }
    }

    fun addInJob(item: OrderClass) {
        val itemInJob = OrderClass(id = item.id, orderName = item.orderName, userID = item.userID, timeToComplit = item.timeToComplit, integerBuy = item.integerBuy, orderON = true)
        viewModelScope.launch {
            orderRepository.addNewBuy(itemInJob)
        }
    }


}