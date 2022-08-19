package com.example.somefood.data.model

import android.content.ClipDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "email") val eMail: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "types") val types: UserTypes,
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "starForCreator") val starForCreator: Double = 0.0,
    @ColumnInfo(name = "starForClient") val starForClient: Double = 0.0,
)

enum class UserTypes {
    CREATOR,
    USER,
}