package miu.edu.cs473.lab8.dao

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import miu.edu.cs473.lab8.model.Plant

class PlantRepository(context: Context) {

    private val plantDao: PlantDao by lazy {
        val database = PlantDatabase.getDatabase(context)
        database.getPlantDao()
    }

    suspend fun insert(plant: Plant) {
        plantDao.insert(plant)
    }

    suspend fun insertAll(plants: List<Plant>) {
        plantDao.insertAll(plants)
    }

    suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    suspend fun delete(plant: Plant) {
        plantDao.delete(plant)
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantDao.getPlantByIdLiveData(plantId)
    }

    fun getAllPlants(): LiveData<List<Plant>> {
        return plantDao.getAllPlants()
    }
}
