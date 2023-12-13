package miu.edu.cs473.foodapp.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import miu.edu.cs473.foodapp.R

data class RecipeModel(
    val name: String,
    val ingredients: String,
    val instructions: String,
    val rating: Double,
    val image: Int,
    val url: String? = null,
    val imgUri: Uri? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Uri::class.java.classLoader) // Read Parcelable Uri
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(ingredients)
        parcel.writeString(instructions)
        parcel.writeDouble(rating)
        parcel.writeInt(image)
        parcel.writeString(url)
        parcel.writeParcelable(imgUri, flags) // Write Parcelable Uri
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<RecipeModel> {
        override fun createFromParcel(parcel: Parcel): RecipeModel =
            RecipeModel(parcel)

        override fun newArray(size: Int): Array<RecipeModel?> =
            arrayOfNulls(size)

        fun createRecipes(): List<RecipeModel> {
            return listOf(
                RecipeModel(
                    "Banh Mi ! (Vietnamese sandwich)",
                    "",
                    "",
                    5.0,
                    R.drawable.vietnamese_sandwich_banh_mi,
                    "https://www.recipetineats.com/banh-mi-vietnamese-sandwich/"
                ),
                RecipeModel(
                    "Vietnamese Phở",
                    "",
                    "",
                    5.0,
                    R.drawable.vietnamese_pho,
                    "https://www.recipetineats.com/vietnamese-pho-recipe/"
                ),
                RecipeModel(
                    "Cơm Tấm (Vietnamese Broken Rice)",
                    "",
                    "",
                    5.0,
                    R.drawable.vietnamese_com_tam,
                    "https://www.authenticfoodquest.com/vietnamese-broken-rice-recipe-com-tam/"
                )
            )
        }
    }
}
