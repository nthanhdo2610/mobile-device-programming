package miu.edu.cs473.lab8.listener

import miu.edu.cs473.lab8.model.Plant

interface DialogListener {
    fun onPlantAdded(plant: Plant)
}
