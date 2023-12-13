package miu.edu.cs473.foodapp.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import miu.edu.cs473.foodapp.R

data class RecipeModel(
    val name: String,
    val instructions: String,
    val rating: Double,
    val image: Int,
    val url: String? = null,
    val imgUri: Uri? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Uri::class.java.classLoader) // Read Parcelable Uri
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
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
                    "Banh Mi is a Vietnamese sandwich that’s made up of an odd sounding combination – crusty bread rolls smeared with pate, mayo, suspicious looking Asian ham, pickled vegetables, green onion, coriander/cilantro, a mighty wack of fresh chillies and drizzle of seasoning.",
                    5.0,
                    R.drawable.vietnamese_sandwich_banh_mi,
                    "https://www.recipetineats.com/banh-mi-vietnamese-sandwich/"
                ),
                RecipeModel(
                    "Vietnamese Phở",
                    "This Pho recipe has been in the works for a while now. It’s been quietly made and remade by various RecipeTin family members since our first trip to Vietnam. We’ve compared notes, debated furiously about how the latest iteration compared to the (many) bowls of Pho soup we slurped during our travels, and our favourite Pho restaurants back home here in Sydney.",
                    5.0,
                    R.drawable.vietnamese_pho,
                    "https://www.recipetineats.com/vietnamese-pho-recipe/"
                ),
                RecipeModel(
                    "Cơm Tấm (Vietnamese Broken Rice)",
                    "This Vietnamese broken rice recipe is for a simple to make iconic Saigon food. Full of flavor, the soft and fluffy broken rice with grilled pork chops and an outstanding fish sauce will excite your taste buds.",
                    5.0,
                    R.drawable.vietnamese_com_tam,
                    "https://www.authenticfoodquest.com/vietnamese-broken-rice-recipe-com-tam/"
                )
            )
        }
    }
}
