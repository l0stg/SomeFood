package com.example.somefood.data.model

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteDataBaseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "name")
    val description: String,
    @ColumnInfo(name = "name")
    val image: Int,
    @ColumnInfo(name = "name")
    val email: String,
)
