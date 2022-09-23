package com.example.somefood.data.model

data class ProductListModel(
    val id: Int,
    val name: String,
    val image: String,
    val recept: String,
    val idFavorite: Int? = null
)


