package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foodFavorite_table")
data class FavoriteModel(
    @PrimaryKey(autoGenerate = true) val idFavorite: Int? = null,
    @ColumnInfo(name = "userID") val userID: Int,
    @ColumnInfo(name = "foodID") val foodId: Int
)
