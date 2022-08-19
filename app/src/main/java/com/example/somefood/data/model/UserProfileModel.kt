package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserProfileModel(
    val eMail: String,
    val types: UserTypes,
    val description: String = "",
    val orderByCreator: Int,
    val orderByClient: Int,
    val ratingByCreator: Double,
    val ratingByClient: Double,
)
