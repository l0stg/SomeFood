package com.example.somefood.data.room

import androidx.room.TypeConverter


class FavoriteConverter {
    @TypeConverter
    fun fromFavorite(favorite: List<Int?>): String = favorite.joinToString()

    @TypeConverter
    fun toFavorite(data: String): List<Int> {
        var newData: List<Int> = listOf()
        if (data != "")
            newData = data.split(", ").map { it.toInt() }.toList()
        return newData
    }
}
