package miu.edu.cs473.lab8.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import miu.edu.cs473.lab8.adapter.GardenLogAdapter
import miu.edu.cs473.lab8.databinding.FragmentGardenLogBinding
import miu.edu.cs473.lab8.listener.DialogListener
import miu.edu.cs473.lab8.listener.GardenLogListener
import miu.edu.cs473.lab8.model.Plant
import miu.edu.cs473.lab8.view.dialog.PlantDialog
import miu.edu.cs473.lab8.view.viewmodel.GardenLogViewModel

class GardenLogFragment
    : BaseFragment<FragmentGardenLogBinding>(FragmentGardenLogBinding::inflate),
    GardenLogListener, DialogListener {

    private lateinit var gardenLogViewModel: GardenLogViewModel
    private lateinit var gardenLogAdapter: GardenLogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (!::gardenLogViewModel.isInitialized) {
            gardenLogViewModel = ViewModelProvider(this)[GardenLogViewModel::class.java]
            this@GardenLogFragment.context?.let {
                gardenLogViewModel.initModel(it)
            }
        }

        if (!::gardenLogAdapter.isInitialized) {
            gardenLogAdapter = GardenLogAdapter(this)
        }

        binding.rvGardenLog.layoutManager = LinearLayoutManager(context)
        binding.rvGardenLog.adapter = gardenLogAdapter

        gardenLogViewModel.allPlants.observe(viewLifecycleOwner, Observer { plants ->
            plants?.let {
                if (it.isEmpty()) {
                    val sampleData = Plant.createGardenLogs()
                    gardenLogAdapter.setData(sampleData)
                    gardenLogViewModel.insertAll(sampleData)
                } else {
                    gardenLogAdapter.setData(it)
                }
                gardenLogAdapter.notifyDataSetChanged()
            }
        })

        binding.btnAction.setOnClickListener {
            val dialog = PlantDialog(this)
            dialog.show(requireActivity().supportFragmentManager, PlantDialog::class.java.name)
        }
    }

    override fun viewGardenLog(plant: Plant) {
        val action = GardenLogFragmentDirections.navigateGardenLogToPlantDetail()
        action.plantId = plant.id
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun onPlantAdded(plant: Plant) {
        gardenLogViewModel.insert(plant)
    }
}
