package com.example.somefood.data.model



data class UserProfileModel(
    val email: String,
    val types: UserTypes,
    val description: String = "",
    val orderByCreator: Int,
    val orderByClient: Int,
    val starForCreator: Double = 0.0,
    val starForClient: Double = 0.0,
)
