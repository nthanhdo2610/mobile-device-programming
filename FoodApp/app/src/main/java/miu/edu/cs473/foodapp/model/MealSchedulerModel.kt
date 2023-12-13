package miu.edu.cs473.foodapp.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class MealSchedulerModel(
    val date: Date,
    val meals: MutableList<MealModel>?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        Date(parcel.readLong()),
        parcel.createTypedArrayList(MealModel.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(date.time)
        parcel.writeTypedList(meals)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<MealSchedulerModel> {
        override fun createFromParcel(parcel: Parcel): MealSchedulerModel =
            MealSchedulerModel(parcel)

        override fun newArray(size: Int): Array<MealSchedulerModel?> =
            arrayOfNulls(size)

        fun createMealSchedulers(): List<MealSchedulerModel> {
            val calendar = Calendar.getInstance()

            return listOf(
                createMealScheduler(
                    Date(calendar.timeInMillis + 2.daysInMillis),
                    listOf(
                        MealModel("Breakfast", "Bánh Mì ! (Vietnamese sandwich)"),
                        MealModel("Lunch", "Vietnamese Phở"),
                        MealModel("Dinner", "Cơm Tấm (Vietnamese Broken Rice)")
                    )
                )
            )
        }

        private fun createMealScheduler(date: Date, meals: List<MealModel>?): MealSchedulerModel =
            MealSchedulerModel(date, meals?.toMutableList())

        private val Int.daysInMillis: Long
            get() = this * 24 * 60 * 60 * 1000L
    }
}
