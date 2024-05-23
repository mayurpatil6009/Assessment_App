package com.example.assessment_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.assessment_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
   lateinit var adapter: ProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)

        val retrofitdata = retrofit.getProducts()

        retrofitdata.enqueue(object : Callback<Products?> {
            override fun onResponse(call: Call<Products?>, response: Response<Products?>) {
                var responcebody = response.body()?.products!!
                adapter = ProductsAdapter(this, responcebody)
               binding.recyclerview.adapter=adapter
                binding.recyclerview.layoutManager=LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<Products?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"error in fetching data",Toast.LENGTH_SHORT).show()
            }
        })
    }
}