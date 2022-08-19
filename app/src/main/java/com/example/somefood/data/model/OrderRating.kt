package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_rating")
data class OrderRating(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    @ColumnInfo(name = "userid") val userId: Int,
    @ColumnInfo(name = "starForCreator") val starForCreator: Double? = 5.0,
    @ColumnInfo(name = "starForClient") val starForClient: Double? = 5.0,
)
