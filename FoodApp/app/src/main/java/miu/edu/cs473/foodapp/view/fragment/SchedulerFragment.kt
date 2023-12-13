package miu.edu.cs473.foodapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import miu.edu.cs473.foodapp.adapter.SchedulerAdapter
import miu.edu.cs473.foodapp.constant.ARG_PARAM1
import miu.edu.cs473.foodapp.constant.ARG_PARAM2
import miu.edu.cs473.foodapp.databinding.FragmentSchedulerBinding
import miu.edu.cs473.foodapp.listener.BaseFragmentListener
import miu.edu.cs473.foodapp.listener.DialogListener
import miu.edu.cs473.foodapp.model.MealSchedulerModel
import miu.edu.cs473.foodapp.view.dialog.SchedulerDialog

class SchedulerFragment : Fragment(), DialogListener, BaseFragmentListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSchedulerBinding
    private lateinit var schedulerAdapter: SchedulerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchedulerBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SchedulerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAdd() {
        val dialog = SchedulerDialog(this)
        dialog.show(parentFragmentManager, SchedulerDialog::class.java.name)
    }

    private fun initViews() {
        if (!this::schedulerAdapter.isInitialized) {
            schedulerAdapter = SchedulerAdapter()
            schedulerAdapter.setData(MealSchedulerModel.createMealSchedulers())
        }

        with(binding.rvMeals) {
            adapter = schedulerAdapter
            layoutManager = LinearLayoutManager(context)
        }

        schedulerAdapter.reloadData()
    }

    override fun addMealScheduler(scheduler: MealSchedulerModel) {
        schedulerAdapter.addMealScheduler(scheduler)
    }
}
