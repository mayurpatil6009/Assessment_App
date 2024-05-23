package com.example.assessment_app

import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {
    @GET("products")
    fun getProducts(): Call<Products>
}