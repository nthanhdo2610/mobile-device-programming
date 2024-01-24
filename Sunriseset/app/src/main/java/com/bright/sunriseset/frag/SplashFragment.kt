package com.bright.sunriseset.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bright.sunriseset.R
import com.bright.sunriseset.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    // Need to declare an object to receive the Navigation arguments from the Generated Args class.
    private val args: SplashFragmentArgs by navArgs()
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Retrieve and set the Arguments received from the Home Fragment
        binding.tvName.text = "Name is ${args.name}"
        binding.tvAge.text = "Age is ${args.age}"
        binding.home.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }
}