package com.bhongj.rc_test_bunjang.src.main.home.brand

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.databinding.BrandIemRecyclerBinding
import com.bhongj.rc_test_bunjang.src.main.home.brand.models.Product

class BrandRcyAdapter(private val productList: MutableList<Product>) :
    RecyclerView.Adapter<BrandRcyAdapter.ViewHolder>() {
    private lateinit var binding: BrandIemRecyclerBinding

    class ViewHolder(binding: BrandIemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgProduct: ImageView = binding.rcyImgProduct
        var imgHeart: ImageView = binding.rcyImgHeart
        var txtPrice: TextView = binding.rcyTxtPrice
        var txtTitle: TextView = binding.rcyTxtTitle

        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bind(item: Product) {
            imgProduct.setImageResource(item.img)
            if (item.bungaePayEnabled) {
                val spannableString = SpannableString("안전 " + item.title)
                spannableString.setSpan(
                    ForegroundColorSpan(Color.parseColor("#1A9C86")),
                    0,
                    2,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    2,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                txtTitle.text = spannableString
            } else {
                txtTitle.text = item.title
            }
            txtPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            BrandIemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productList[position]

        holder.imgHeart.setOnClickListener {
            if (item.imgHeartCheck) {
                item.imgHeartCheck = false
                holder.imgHeart.setImageResource(R.drawable.img_heart)
                holder.imgHeart.setColorFilter(Color.argb(255, 255, 255, 255))
            } else {
                item.imgHeartCheck = true
                holder.imgHeart.setImageResource(R.drawable.img_heart_checked)
                holder.imgHeart.setColorFilter(Color.argb(0, 0, 0, 0))
            }
            Log.d("TEST brand rcy imgHeartCheck", item.imgHeartCheck.toString())
            Log.d("TEST brand rcy position", position.toString())
        }

        holder.bind(item)
    }

    override fun getItemCount() = productList.size
}