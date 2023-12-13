package miu.edu.cs473.foodapp.listener

import miu.edu.cs473.foodapp.model.BlogModel
import miu.edu.cs473.foodapp.model.MealSchedulerModel
import miu.edu.cs473.foodapp.model.RecipeModel
import miu.edu.cs473.foodapp.model.UserModel

interface DialogListener {
    fun addData(model: UserModel) {}
    fun addRecipe(recipe: RecipeModel) {}
    fun addMealScheduler(scheduler: MealSchedulerModel) {}
    fun addBlog(blogModel: BlogModel) {}
}