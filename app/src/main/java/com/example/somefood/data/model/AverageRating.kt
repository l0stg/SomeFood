package com.example.somefood.data.model

import androidx.room.ColumnInfo

data class AverageRating(
    @ColumnInfo(name = "starForCreator") val starForCreator: Double,
    @ColumnInfo(name = "starForClient") val starForClient: Double
)
