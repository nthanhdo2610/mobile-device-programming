package com.bright.sunriseset.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bright.sunriseset.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            btnSend.setOnClickListener {
                // Navigate through Generated Directions through the action Home Fragment to SplashFragment
                val directions = HomeFragmentDirections.actionHomeFragmentToSplashFragment(
                    etName.text.toString(),
                    etAge.text.toString().toInt()
                )
                // Calling this on a Fragment to navigate your Directions
                findNavController().navigate(directions)
            }
        }
        return binding.root
    }
}