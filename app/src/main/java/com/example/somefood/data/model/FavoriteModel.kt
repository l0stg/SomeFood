package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foodFavorite_table")
data class FavoriteModel(
    @PrimaryKey(autoGenerate = true) val idFavorite: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "foodId") val foodId: Int
)
