package miu.edu.cs473.lab7

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductList(val products: List<Product>) : Parcelable