package com.example.somefood.ui.bottomSheetFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.OrderRating
import com.example.somefood.data.model.Status
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.data.room.repository.UserRatingRepositiry
import kotlinx.coroutines.launch

class NewOrderBottomSheetViewModel(
    private val repositoryOrder: OrderRepository,
    private val repositoryUser: RepositoryUser,
    private val orderRating: UserRatingRepositiry,
) : ViewModel() {

    fun addNewOrder(time: String, price: String, itemName: String, itemImage: String, foodId: Int) {
        viewModelScope.launch {
            increaseOrders()
            val newOrderId = repositoryOrder.addNewBuy(
                Order(
                    orderName = itemName,
                    userID = repositoryUser.getUserID(),
                    timeToComplete = time,
                    integerBuy = price.toInt(),
                    status = Status.WAIT,
                    image = itemImage,
                    foodId = foodId,
                )
            )
            orderRating.updateUserRating(
                OrderRating(
                    orderId = newOrderId.toInt(),
                    userid = repositoryUser.getUserID(),
                    starForCreator = null,
                    starForClient = null
                )
            )
        }
    }

    private fun increaseOrders() {
        viewModelScope.launch {
            repositoryUser.increaseOrdersByClient(userId = repositoryUser.getUserID())
        }
    }
}