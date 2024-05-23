package com.example.assessment_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import retrofit2.Callback

class ProductsAdapter(val context: Callback<Products?>, val productlist: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.prodcutTitle)
        val image = itemView.findViewById<ShapeableImageView>(R.id.productImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_product, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productlist[position]
        holder.title.text = currentItem.title
        Glide.with(context)
            .load(currentItem.images)
            .into(holder.image)
    }
}