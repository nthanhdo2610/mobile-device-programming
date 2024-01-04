package miu.edu.cs473.lab8.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import miu.edu.cs473.lab8.databinding.FragmentPlantDetailBinding
import miu.edu.cs473.lab8.model.Plant
import miu.edu.cs473.lab8.view.viewmodel.PlantDetailViewModel

class PlantDetailsFragment : BaseFragment<FragmentPlantDetailBinding>(FragmentPlantDetailBinding::inflate) {

    private lateinit var viewModel: PlantDetailViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observePlantDetails()
    }

    private fun observePlantDetails() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this)[PlantDetailViewModel::class.java]
            this@PlantDetailsFragment.context?.let {
                viewModel.initModel(it)
            }
        }

        val plantId = arguments?.getInt("plantId") ?: 0
        viewModel.getPlantById(plantId).observe(viewLifecycleOwner, Observer { plant ->
            plant?.let {
                displayPlantDetail(it)
            }
        })
    }

    private fun displayPlantDetail(plant: Plant) {
        binding.tvName.text = plant.name
        binding.tvType.text = "Type: ${plant.type}"
        binding.tvWateringFrequency.text = "Watering Frequency: ${plant.wateringFrequency} days"
        binding.tvPlantingDate.text = "Planting Date: ${plant.plantingDateString}"
    }
}