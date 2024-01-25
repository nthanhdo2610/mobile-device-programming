package com.bright.sunriseset.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bright.sunriseset.dao.NoteDao
import com.bright.sunriseset.entity.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    // Build RoomDB
    companion object {
        //  means that this field is immediately made visible to other threads
        @Volatile
        private var instance: NoteDatabase? = null
        private val LOCK = Any() // The root of the Kotlin class hierarchy. Every Kotlin class has [Any] as a superclass.

        /*  Help of ?: elvis operator check if the instance is not null return the instance,
            if it is null then synchronized block will  work, inside this also check null or not and call the function buildDatabase*/
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // Create an instance also return the instance
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        // Function to build database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notedatabase"
        ).build()
    }
}