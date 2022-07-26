package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "email") val eMail: String,
    @ColumnInfo(name = "password") val password: String
)
