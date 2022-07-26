package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey private val id: Int,
    @ColumnInfo(name = "email") private val eMail: String,
    @ColumnInfo(name = "password") private val password: String
)
