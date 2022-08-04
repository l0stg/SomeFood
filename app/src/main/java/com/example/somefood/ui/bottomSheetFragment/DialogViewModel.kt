package com.example.somefood.ui.bottomSheetFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryUser
import kotlinx.coroutines.launch

class DialogViewModel(
    private val repositoryOrder: OrderRepository,
    private val repositoryUser: RepositoryUser,
): ViewModel() {

    fun addNewOrder(time: String, price: String, itemName: String) {
        val newItem = OrderClass(
            orderName = itemName,
            userID = repositoryUser.getUserID(),
            timeToComplit = time,
            integerBuy = price.toInt(),
            orderON = false
        )
        viewModelScope.launch {
            repositoryOrder.addNewBuy(newItem)
        }
    }

}