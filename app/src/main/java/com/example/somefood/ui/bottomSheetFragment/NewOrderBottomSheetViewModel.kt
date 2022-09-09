package com.example.somefood.ui.bottomSheetFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.OrderRating
import com.example.somefood.data.model.Status
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.data.room.repository.UserRatingRepositiry
import com.example.somefood.ui.Event.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewOrderBottomSheetViewModel(
    private val repositoryOrder: OrderRepository,
    private val repositoryUser: RepositoryUser,
    private val orderRating: UserRatingRepositiry,
) : ViewModel() {

    private val _validOrder = MutableStateFlow<Event<Boolean>?>(null)
    val validOrder = _validOrder

    fun addNewOrder(
        hour: Int,
        minute: Int,
        price: String,
        itemName: String,
        itemImage: String,
        foodId: Int
    ) {
        viewModelScope.launch {

            if (checkValidation(price) && (hour != 0 || minute != 0)) {
                increaseOrders()
                val newOrderId = repositoryOrder.addNewBuy(
                    Order(
                        orderName = itemName,
                        userID = repositoryUser.getUserID(),
                        timeToCompleteHour = hour,
                        timeToCompleteMinutes = minute,
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
                _validOrder.value = Event(false)
            } else {
                _validOrder.value = Event(true)
            }
        }
    }

    private fun increaseOrders() {
        viewModelScope.launch {
            repositoryUser.increaseOrdersByClient(userId = repositoryUser.getUserID())
        }
    }

    private fun checkValidation(buyPrice: String): Boolean {
        return (buyPrice.isNotEmpty() && !buyPrice.startsWith("0") && !buyPrice.startsWith("-"))
    }
}