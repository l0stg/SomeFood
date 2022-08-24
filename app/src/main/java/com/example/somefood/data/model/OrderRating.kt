package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_rating")
data class OrderRating(
    @PrimaryKey(autoGenerate = true) val orderId: Int,
    @ColumnInfo(name = "userid") val userid: Int,
    @ColumnInfo(name = "starForCreator") val starForCreator: Double,
    @ColumnInfo(name = "starForClient") val starForClient: Double,
)
