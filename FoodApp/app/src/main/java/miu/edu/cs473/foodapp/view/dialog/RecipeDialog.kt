package miu.edu.cs473.foodapp.view.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputFilter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import miu.edu.cs473.foodapp.R
import miu.edu.cs473.foodapp.databinding.DialogRecipeBinding
import miu.edu.cs473.foodapp.listener.DialogListener
import miu.edu.cs473.foodapp.model.RecipeModel
import miu.edu.cs473.foodapp.util.MinMaxFilter

class RecipeDialog(private val listener: DialogListener) : DialogFragment() {

    private lateinit var binding: DialogRecipeBinding
    private var imgUri: Uri? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogRecipeBinding.inflate(layoutInflater)
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Error!!!")

        initViews()

        return dialog
    }

    private fun initViews() {
        binding.etRatings.filters = arrayOf<InputFilter>(MinMaxFilter(1.0, 5.0))

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnAdd.setOnClickListener {
            addRecipe()
        }

        binding.imv.setOnClickListener {
            pickImage()
        }
    }

    private fun addRecipe() {
        val name = binding.etName.text.toString().trim()
        if (name.isEmpty()) {
            showToast("Please input name")
            return
        }
        val instructions = binding.etInstructions.text.toString().trim()
        if (instructions.isEmpty()) {
            showToast("Please input instructions")
            return
        }
        val ratings = binding.etRatings.text.toString().trim()
        if (ratings.isEmpty()) {
            showToast("Please input ratings")
            return
        }
        val rating = ratings.toDoubleOrNull() ?: 1.0
        val url = binding.etLink.text.toString().trim()

        listener.addRecipe(
            RecipeModel(
                name,
                instructions,
                rating,
                R.drawable.book,
                url,
                imgUri
            )
        )
        dismiss()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun pickImage() {
        val pickImg = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        changeImage.launch(pickImg)
    }

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                imgUri = data?.data
                binding.imv.setImageURI(imgUri)
            }
        }
}
