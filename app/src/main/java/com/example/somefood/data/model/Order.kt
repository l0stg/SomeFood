package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "orderName") val orderName: String,
    @ColumnInfo(name = "userID") val userID: Int,
    @ColumnInfo(name = "timeToComplit") val timeToComplit: String,
    @ColumnInfo(name = "integerBuy") val integerBuy: Int,
    @ColumnInfo(name = "status") val status: Status,
    @ColumnInfo(name = "image") val image: Int,
    @ColumnInfo(name = "userIdGoToJob") val userIdGoToJob: Int = -1,
)

enum class Status(val status: String) {
    WAIT("В ожидании"),
    JOB("В работе"),
    COMPLIT("Готово"),
}
