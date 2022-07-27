package com.example.somefood.data.room

import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors


class FavoriteConverter {
    @TypeConverter
    fun from(favorite: List<Int?>): String {
        return favorite.joinToString()
    }

    @TypeConverter
    fun to(data: String): List<Int> {
        val i: List<String> =  data.split(",")
        val g: MutableList<Int> = mutableListOf()
        i.forEach {
            g.add(it.toInt())
        }
        return g
    }
}
