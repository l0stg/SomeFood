package com.example.somefood.ui.bottomSheetFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.room.repository.OrderRepository
import kotlinx.coroutines.launch

class DialogViewModel(
    private val repositoryOrder: OrderRepository,
): ViewModel() {

    fun addNewOrder(newItem: OrderClass) {
        viewModelScope.launch {
            repositoryOrder.addNewBuy(newItem)
        }
    }

}