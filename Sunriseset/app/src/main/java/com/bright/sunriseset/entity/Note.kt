package com.bright.sunriseset.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    val title: String,
    val content: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
