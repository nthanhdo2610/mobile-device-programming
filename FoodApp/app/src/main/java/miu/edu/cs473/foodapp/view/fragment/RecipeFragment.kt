package miu.edu.cs473.foodapp.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import miu.edu.cs473.foodapp.adapter.RecipeAdapter
import miu.edu.cs473.foodapp.databinding.FragmentRecipeBinding
import miu.edu.cs473.foodapp.view.dialog.RecipeDialog
import miu.edu.cs473.foodapp.listener.BaseFragmentListener
import miu.edu.cs473.foodapp.listener.DialogListener
import miu.edu.cs473.foodapp.listener.RecipeListener
import miu.edu.cs473.foodapp.model.RecipeModel
import miu.edu.cs473.foodapp.constant.ARG_PARAM1
import miu.edu.cs473.foodapp.constant.ARG_PARAM2
import miu.edu.cs473.foodapp.view.WebviewActivity

class RecipeFragment : Fragment(), RecipeListener, DialogListener, BaseFragmentListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var recipeAdapter: RecipeAdapter

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
        binding = FragmentRecipeBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initViews() {
        if (!this::recipeAdapter.isInitialized) {
            recipeAdapter = RecipeAdapter(this)
            recipeAdapter.setData(RecipeModel.createRecipes())
        }
        with(binding.rvRecipe) {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(context)
        }
        recipeAdapter.reloadData()
    }

    override fun onAdd() {
        val dialog = RecipeDialog(this)
        dialog.show(parentFragmentManager, RecipeDialog::class.java.name)
    }

    override fun viewRecipe(recipe: RecipeModel) {
        val url = recipe.url ?: ""
        if (url.isNotEmpty()) {
            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra("currentUrl", url)
            startActivity(intent)
        }
    }

    override fun addRecipe(recipe: RecipeModel) {
        recipeAdapter.addRecipe(recipe)
    }
}
