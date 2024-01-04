package miu.edu.cs473.lab8.view.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import miu.edu.cs473.lab8.dao.PlantRepository
import miu.edu.cs473.lab8.model.Plant

class PlantDetailViewModel : ViewModel() {

    private lateinit var plantRepository: PlantRepository

    fun initModel(context: Context) {
        plantRepository = PlantRepository(context)
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantRepository.getPlantById(plantId)
    }
}
