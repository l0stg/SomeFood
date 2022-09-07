package com.example.somefood.data.model

import android.os.Parcel
import android.os.Parcelable

data class ProductListModel(
    val id: Int,
    val name: String,
    val image: String,
    val recept: String,
    val idFavorite: Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(recept)
        if (idFavorite != null) {
            parcel.writeInt(idFavorite)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductListModel> {
        override fun createFromParcel(parcel: Parcel): ProductListModel {
            return ProductListModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductListModel?> {
            return arrayOfNulls(size)
        }
    }
}

