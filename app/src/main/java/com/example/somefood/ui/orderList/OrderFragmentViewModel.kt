package com.example.somefood.ui.orderList

import androidx.lifecycle.ViewModel
import com.example.somefood.data.model.OrderClass
import com.example.somefood.data.room.repository.OrderRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class OrderFragmentViewModel(
    private val orderRepository: OrderRepository,
    private val router: Router
): ViewModel() {

    private val _list = MutableStateFlow<List<OrderClass>>(emptyList())
    val list: Flow<List<OrderClass>> = _list





}