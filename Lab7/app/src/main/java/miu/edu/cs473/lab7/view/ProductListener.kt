package miu.edu.cs473.lab7.view

import miu.edu.cs473.lab7.model.Product

interface ProductListener {
    fun selectProduct(product: Product)
    fun viewProduct(product: Product)
}