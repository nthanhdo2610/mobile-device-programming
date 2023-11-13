package miu.edu.cs473.lab7.model

import android.os.Parcel
import android.os.Parcelable
import miu.edu.cs473.lab7.R

data class Product(
    val name: String?,
    val desc: String?,
    val price: Double,
    val image: Int,
    val icon: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeDouble(price)
        parcel.writeInt(image)
        parcel.writeInt(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }

        fun createProducts(): ArrayList<Product> {
            return arrayListOf(
                Product(
                    "iPhone 15", "Black, Ram 515 GB\nProduced in 2023",
                    7000.70, R.drawable.iphone, R.drawable.icon_iphone
                ),

                Product(
                    "iPad Pro", "Blue, Ram 256 GB\nProduced in 2022",
                    5000.0, R.drawable.ipad, R.drawable.icon_ipad
                ),

                Product(
                    "Macbook pro", "Ram 515 GB, Core i9\nProduced in 2023",
                    17000.0, R.drawable.macbook, R.drawable.icon_macbook
                ),

                Product(
                    "iWatch", "White, 3rd generation\nProduced in 2021",
                    800.20, R.drawable.iwatch, R.drawable.icon_iwatch
                ),

                Product(
                    "Airpod", "Silver, Wireless charging\nProduced in 2020",
                    500.0, R.drawable.airpod, R.drawable.icon_airpod
                ),

                Product(
                    "SmartTV", "TCL branch\nProduced in 2019",
                    4000.0, R.drawable.smarttv, R.drawable.icon_smarttv
                ),

                Product(
                    "Fridge", "Black\nProduced in 2023",
                    3000.0, R.drawable.fridge, R.drawable.icon_fridge
                )
            )
        }
    }
}