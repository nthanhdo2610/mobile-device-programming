package miu.edu.cs473.lab8.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@SuppressLint("SimpleDateFormat")
val dateFormatter = SimpleDateFormat("yyyy-MM-dd")

@Entity(tableName = "Plant")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: String,
    val wateringFrequency: Int,
    val plantingDateString: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeInt(wateringFrequency)
        parcel.writeString(plantingDateString)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plant> {
        override fun createFromParcel(parcel: Parcel): Plant {
            return Plant(parcel)
        }

        override fun newArray(size: Int): Array<Plant?> {
            return arrayOfNulls(size)
        }

        fun createGardenLogs(): List<Plant> {
            val currentDate = Instant.now().toEpochMilli()
            return arrayListOf(
                Plant(1, "Rose", "Flower", 1, dateFormatter.format(Date(currentDate + 24 * 60 * 60 * 1000))),
                Plant(2, "Tomato", "Vegetable", 3, dateFormatter.format(Date(currentDate - 24 * 60 * 60 * 1000))),
                Plant(3, "Basil", "Herb", 2, dateFormatter.format(Date(currentDate + 2 * 24 * 60 * 60 * 1000)))
            )
        }
    }
}
