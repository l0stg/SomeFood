package com.example.somefood.data.model

import android.os.Parcel
import android.os.Parcelable

data class ProductListModel(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val favoriteInUser: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeByte(if (favoriteInUser) 1 else 0)
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

