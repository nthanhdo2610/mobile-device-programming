package miu.edu.cs473.lab8.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import miu.edu.cs473.lab8.model.Plant

@Dao
interface PlantDao {
    @Insert
    suspend fun insert(plant: Plant)

    @Insert
    suspend fun insertAll(plants: List<Plant>)

    @Update
    suspend fun update(plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)

    @Query("DELETE FROM Plant WHERE id = :plantId")
    suspend fun deleteById(plantId: Int)

    @Query("DELETE FROM Plant")
    suspend fun deleteAll()

    @Query("SELECT * FROM Plant WHERE id = :plantId")
    fun getPlantByIdLiveData(plantId: Int): LiveData<Plant>

    @Query("SELECT * FROM Plant ORDER BY id DESC")
    fun getAllPlants(): LiveData<List<Plant>>

    @Query("SELECT * FROM Plant WHERE id = :plantId")
    suspend fun getPlantById(plantId: Int): Plant
}
