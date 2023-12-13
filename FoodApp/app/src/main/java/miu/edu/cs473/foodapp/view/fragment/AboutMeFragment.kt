package miu.edu.cs473.foodapp.view.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import miu.edu.cs473.foodapp.databinding.FragmentAboutMeBinding
import miu.edu.cs473.foodapp.listener.BaseFragmentListener
import miu.edu.cs473.foodapp.listener.DialogListener
import miu.edu.cs473.foodapp.model.UserModel
import miu.edu.cs473.foodapp.constant.ARG_PARAM1
import miu.edu.cs473.foodapp.constant.ARG_PARAM2

class AboutMeFragment : Fragment(), DialogListener, BaseFragmentListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAboutMeBinding
    private var changeImage: ActivityResultLauncher<Intent>? = null
    private var imgUri: Uri? = null
    private var strInformation: String? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        initChangeImage()
    }

    override fun onStart() {
        super.onStart()
        initChangeImage()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        changeImage = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutMeBinding.inflate(layoutInflater)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.imv.setOnClickListener {
            changeImage?.let {
                val pickImg = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                )
                it.launch(pickImg)
            }
        }

        imgUri?.let {
            binding.imv.setImageURI(it)
        }

        if (strInformation == null) {
            strInformation = binding.tvDesc.text.toString().trim()
        } else {
            binding.tvDesc.text = strInformation
        }

        if (sharedPreferences == null) {
            sharedPreferences = context?.getSharedPreferences("user_login", Context.MODE_PRIVATE)
        }

        val firstName = sharedPreferences?.getString("first_name", "") ?: "Domenic"
        val lastName = sharedPreferences?.getString("last_name", "") ?: "Nguyen"
        binding.tvAuthor.text = "$lastName, $firstName"
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutMeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAdd() {
        Toast.makeText(requireContext(), "$strInformation", Toast.LENGTH_SHORT).show()
    }

    override fun addData(model: UserModel) {
        val msg = "${binding.tvDesc.text.toString().trim()}\n\n${model.name}: ${model.desc}"
        binding.tvDesc.text = msg
        strInformation = msg
    }

    private fun initChangeImage() {
        if (changeImage == null) {
            changeImage = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val data = it.data
                    imgUri = data?.data
                    binding.imv.setImageURI(imgUri)
                }
            }
        }
    }
}