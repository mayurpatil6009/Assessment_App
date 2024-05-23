package com.example.assessment_app

data class Products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)