package com.example.somefood.data.model

import android.os.Parcel
import android.os.Parcelable

data class ProductListModel(
    val id: Int,
    val name: String,
    val image: Int,
    val description: String,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeString(description)
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
