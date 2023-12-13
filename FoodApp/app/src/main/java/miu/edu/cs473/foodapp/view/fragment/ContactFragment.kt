package miu.edu.cs473.foodapp.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import miu.edu.cs473.foodapp.databinding.FragmentContactBinding
import miu.edu.cs473.foodapp.listener.BaseFragmentListener
import miu.edu.cs473.foodapp.constant.ARG_PARAM1
import miu.edu.cs473.foodapp.constant.ARG_PARAM2

class ContactFragment : Fragment(), BaseFragmentListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentContactBinding

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
        binding = FragmentContactBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAdd() {
        Toast.makeText(requireContext(), "Domenic", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        binding.cvMobile.setOnClickListener { openMobilePhone() }
    }

    private fun openMobilePhone() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567890"))
        startActivity(intent)
    }

}
