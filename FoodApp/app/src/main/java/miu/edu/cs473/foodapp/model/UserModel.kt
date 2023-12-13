package miu.edu.cs473.foodapp.model

import android.os.Parcel
import android.os.Parcelable

open class UserModel(
    val name: String = "",
    val desc: String = ""
) : Parcelable {
    init {
        requireNotNull(name) { "Name cannot be null" }
        requireNotNull(desc) { "Description cannot be null" }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}
