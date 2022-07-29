package com.example.somefood.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "order_table")
data class OrderClass(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "orderName") val orderName: String,
    @ColumnInfo(name = "nameZakazchik") val nameZakazchik: String,
    @ColumnInfo(name = "timeToComplit") val timeToComplit: String,
    @ColumnInfo(name = "integerBuy") val integerBuy: Int
)
