package com.example.somefood.data.model


data class UserProfileModel(
    val email: String,
    val types: UserTypes,
    val photoProfile: String,
    val description: String,
    val ordersAsCreator: Int,
    val ordersAsClient: Int,
    val starForCreator: Double,
    val starForClient: Double,
)
