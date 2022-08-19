package com.example.somefood.data.model

import androidx.room.ColumnInfo


data class UserProfileModel(
    val email: String,
    val types: UserTypes,
    val photoProfile: String = "",
    val description: String = "",
    val orderByCreator: Int,
    val orderByClient: Int,
    val starForCreator: Double = 0.0,
    val starForClient: Double = 0.0,
)
