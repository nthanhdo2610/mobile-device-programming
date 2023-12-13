package miu.edu.cs473.foodapp.model

import android.os.Parcel
import android.os.Parcelable

data class BlogModel(
    val name: String,
    val desc: String?,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BlogModel> {
        override fun createFromParcel(parcel: Parcel): BlogModel {
            return BlogModel(parcel)
        }

        override fun newArray(size: Int): Array<BlogModel?> {
            return arrayOfNulls(size)
        }

        // Static factory method for creating sample blogs
        fun createSampleBlogs(): ArrayList<BlogModel> {
            return arrayListOf(
                BlogModel(
                    "THE HISTORY OF BÁNH MÌ",
                    "Bánh Mì (pronounced BUN-mee) is the Vietnamese term for “bread”, but it also refers to a special kind of sandwich: a culinary fusion of two cultures and a prime example of how food is always tied with history…",
                    "https://banhmibistroaz.com/banh-mi/"
                ),
                BlogModel(
                    "Homemade Vietnamese Phở",
                    "Learn how to make delicious Vietnamese pho soup from scratch with a light and aromatic beef broth infused with ginger, star anise, coriander, and cinnamon. Making pho at home is not hard, all you need is a straightforward recipe, a few secrets, and a nice big stockpot.",
                    "https://www.inspiredtaste.net/4307/vietnamese-soup-pho/"
                ),
                BlogModel(
                    "What Is Cơm Tấm? An Introduction To Ho Chi Minh City’s Famous Broken Rice Dish And The Best Place To Get It",
                    "Learn how to make delicious Vietnamese pho soup from scratch with a light and aromatic beef broth infused with ginger, star anise, coriander, and cinnamon. Making pho at home is not hard, all you need is a straightforward recipe, a few secrets, and a nice big stockpot.Smoke wafts in the air, as the kitchen exhaust struggles to keep up, so that the enticing char-grilled aroma tempts you the moment you turn the corner of Đ. Đặng Văn Ngữ street in Ho Chi Minh City.",
                    "https://guide.michelin.com/vn/en/article/dining-out/what-is-com-tam-com-tam-ba-ghien"
                )
            )
        }
    }
}
