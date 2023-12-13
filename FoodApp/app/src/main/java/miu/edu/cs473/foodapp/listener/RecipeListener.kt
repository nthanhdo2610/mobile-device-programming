package miu.edu.cs473.foodapp.listener

import miu.edu.cs473.foodapp.model.RecipeModel

interface RecipeListener {
    fun viewRecipe(recipe: RecipeModel)
}