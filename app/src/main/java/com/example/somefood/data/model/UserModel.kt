package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "email") val eMail: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "types") val types: UserTypes
)

enum class UserTypes {
    CREATOR,
    USER,
}