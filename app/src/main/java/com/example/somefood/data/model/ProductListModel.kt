package com.example.somefood.data.model

import android.media.Image
import java.io.Serializable

data class ProductListModel(
    val name: String,
    val image: Int,
    val description: String,
    var favorite: Boolean
): Serializable
