package miu.edu.cs473.foodapp.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import miu.edu.cs473.foodapp.databinding.DialogBlogBinding
import miu.edu.cs473.foodapp.listener.DialogListener
import miu.edu.cs473.foodapp.model.BlogModel

class BlogDialog(val listener: DialogListener) : DialogFragment() {

    private lateinit var binding: DialogBlogBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogBlogBinding.inflate(layoutInflater)
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Error!!!")

        initViews()

        return dialog
    }

    private fun initViews() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnAdd.setOnClickListener {
            addBlog()
        }
    }

    private fun addBlog() {
        val name = binding.etBlogName.text.toString().trim()
        if (name.isEmpty()) {
            showToast("Please input Blog name")
            return
        }

        val desc = binding.etDesc.text.toString().trim()
        if (desc.isEmpty()) {
            showToast("Please input Blog description")
            return
        }

        val url = binding.etLink.text.toString().trim()
        if (url.isEmpty()) {
            showToast("Please input Blog link")
            return
        }

        listener.addBlog(BlogModel(name, desc, url))
        dismiss()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}
