package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import android.inputmethodservice.Keyboard
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhongj.rc_test_bunjang.databinding.ProductIemRecyclerBinding
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.Product

class ProductRcyAdapter(private val productList: MutableList<Product>) :
    RecyclerView.Adapter<ProductRcyAdapter.ViewHolder>() {
    private lateinit var binding: ProductIemRecyclerBinding

    class ViewHolder(binding: ProductIemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgProduct: ImageView = binding.rcyImgProduct
        var imgHeart: ImageView = binding.rcyImgHeart
        var imgBungae: ImageView = binding.rcyImgBungae
        var txtRegion: TextView = binding.rcyTxtRegion
        var txtPrice: TextView = binding.rcyTxtPrice
        var txtTitle: TextView = binding.rcyTxtTitle

        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bind(item: Product) {
            imgProduct.setImageResource(item.img)
            if (item.bungaePayEnabled) {
                imgBungae.visibility = View.VISIBLE
            } else {
                imgBungae.visibility = View.INVISIBLE
            }
            txtRegion.text = item.region
            txtTitle.text = item.title
            txtPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ProductIemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productList[position]

        holder.bind(item)
    }

    override fun getItemCount() = productList.size
}