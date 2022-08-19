package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_rating")
data class UserRating(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    @ColumnInfo(name = "userid") val userId: Int,
    @ColumnInfo(name = "starForCreator") val starForCreator: Double? = 0.0,
    @ColumnInfo(name = "starForClient") val starForClient: Double? = 0.0,
)
