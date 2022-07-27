package com.example.somefood.data.model

import androidx.room.*
import com.example.somefood.data.room.FavoriteConverter
import java.io.Serializable

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "email") val eMail: String,
    @ColumnInfo(name = "password") val password: String,
    @TypeConverters(FavoriteConverter::class)
    @ColumnInfo(name = "favorite") var favorite: List<Int>? = null
): Serializable
