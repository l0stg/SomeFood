package com.example.somefood.data.model

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "email") val eMail: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "types") val types: Boolean
): Serializable
