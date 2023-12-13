package miu.edu.cs473.foodapp.listener

import miu.edu.cs473.foodapp.model.BlogModel

interface BlogListener {
    fun viewBlog(blog: BlogModel)
}