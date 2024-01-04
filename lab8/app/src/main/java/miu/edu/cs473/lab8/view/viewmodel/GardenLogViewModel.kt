package miu.edu.cs473.lab8.view.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miu.edu.cs473.lab8.dao.PlantRepository
import miu.edu.cs473.lab8.model.Plant

class GardenLogViewModel : ViewModel() {

    lateinit var allPlants: LiveData<List<Plant>>
    private lateinit var plantRepository: PlantRepository

    fun initModel(context: Context) {
        plantRepository = PlantRepository(context)
        allPlants = plantRepository.getAllPlants()
    }

    fun insertAll(plants: List<Plant>) {
        viewModelScope.launch {
            plantRepository.insertAll(plants)
        }
    }

    fun insert(plant: Plant) {
        viewModelScope.launch {
            plantRepository.insert(plant)
        }
    }
}
