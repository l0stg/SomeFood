package com.example.somefood.data.model

import android.content.ClipDescription
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val userUID: Int = 0,
    @ColumnInfo(name = "email") val eMail: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "types") val types: UserTypes,
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "orderByClient") val orderByClient: Int = 0,
    @ColumnInfo(name = "orderByCreator") val orderByCreator: Int = 0,
)

enum class UserTypes {
    CREATOR,
    USER,
}