package com.example.somefood.data.model


data class UserProfileModel(
    val email: String = "",
    val types: UserTypes = UserTypes.USER,
    val photoProfile: String = "",
    val description: String = "",
    val ordersAsCreator: Int = 0,
    val ordersAsClient: Int = 0,
    val starForCreator: Double = 0.0,
    val starForClient: Double = 0.0,
)
