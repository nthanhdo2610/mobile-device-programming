package miu.edu.cs473.lab7.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String?,
    val desc: String?,
    val price: Double,
    val image: Int,
    val icon: Int
) : Parcelable
