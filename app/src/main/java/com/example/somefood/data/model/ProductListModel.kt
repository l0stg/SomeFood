package com.example.somefood.data.model

import android.os.Parcel
import android.os.Parcelable

data class ProductListModel(
    val id: Int,
    val name: String,
    val image: String,
    val recept: String,
    val idFavorite: Int? = null
)


