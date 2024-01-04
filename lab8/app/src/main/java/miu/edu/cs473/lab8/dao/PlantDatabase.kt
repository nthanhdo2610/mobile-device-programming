package miu.edu.cs473.lab8.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import miu.edu.cs473.lab8.model.Plant

@Database(
    entities = [Plant::class],
    version = 1,
    exportSchema = false
)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun getPlantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null
        private val LOCK = Any()

        fun getDatabase(context: Context): PlantDatabase {
            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PlantDatabase::class.java,
                "plant_database"
            ).build()
    }
}
