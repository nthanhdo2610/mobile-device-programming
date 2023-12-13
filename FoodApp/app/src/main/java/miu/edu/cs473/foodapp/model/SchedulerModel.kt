package miu.edu.cs473.foodapp.model

import android.os.Parcel
import android.os.Parcelable

data class SchedulerModel(
    val name: String,
    val meal: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(meal)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<SchedulerModel> {
        override fun createFromParcel(parcel: Parcel): SchedulerModel =
            SchedulerModel(parcel)

        override fun newArray(size: Int): Array<SchedulerModel?> =
            arrayOfNulls(size)
    }
}
