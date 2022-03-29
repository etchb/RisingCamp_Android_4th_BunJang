package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_IDX
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.sSharedPreferences
import com.bhongj.rc_test_bunjang.databinding.ProductIemRecyclerBinding
import com.bhongj.rc_test_bunjang.src.main.detailPage.ProductDetailActivity
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.FavoritesRequest
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.FavoritesResponse
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndItem
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndResponse
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class ProductRcyAdapter(
    val rootActivity: Activity,
    private val productList: MutableList<HomeRecmndItem>
) :
    RecyclerView.Adapter<ProductRcyAdapter.ViewHolder>(), RecmndFragmentInterface {
    private lateinit var binding: ProductIemRecyclerBinding

    class ViewHolder(binding: ProductIemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgProduct: ImageView = binding.rcyImgProduct
        var imgHeart: ImageView = binding.rcyImgHeart
        var imgBungae: ImageView = binding.rcyImgBungae
        var txtRegion: TextView = binding.rcyTxtRegion
        var txtPrice: TextView = binding.rcyTxtPrice
        var txtTitle: TextView = binding.rcyTxtTitle
        var txtSmallHeartCnt: TextView = binding.rcyTxtSmallHeartCnt
        var linlaySmallHeart: LinearLayout = binding.rcyLinlaySmallHeart

        init {
            // Define click listener for the ViewHolder's View.
        }

        fun bind(rootActivity: Activity, item: HomeRecmndItem) {
            Glide.with(rootActivity)
                .load(item.imageUrl)
                .into(imgProduct)
            if (item.saftyPay == 1) {
                imgBungae.visibility = View.VISIBLE
                val spannableString = SpannableString("안전 " + item.productName)
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
                imgBungae.visibility = View.GONE
                txtTitle.text = item.productName
            }
            if (item.productLike > 0) {
                linlaySmallHeart.visibility = View.VISIBLE
                txtSmallHeartCnt.text = item.productLike.toString()
            } else {
                linlaySmallHeart.visibility = View.GONE
            }
            txtRegion.text = item.directtrans

            val t_dec_up = DecimalFormat("#,###")
            t_dec_up.format(item.price)
            txtPrice.text = t_dec_up.format(item.price).toString() + "원"

            if (item.myLike == 0) {
                imgHeart.setImageResource(R.drawable.img_heart)
                imgHeart.setColorFilter(Color.argb(255, 255, 255, 255))
            } else {
                imgHeart.setImageResource(R.drawable.img_heart_checked)
                imgHeart.setColorFilter(Color.argb(0, 0, 0, 0))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ProductIemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productList[position]

        holder.imgHeart.setOnClickListener {
            val idx = item.idx
            RecmndService(this).tryPostData(
                FavoritesRequest(
                    userIdx = sSharedPreferences.getInt(MY_IDX, 0),
                    productIdx = idx
                )
            )
            if (item.myLike == 1) {
                item.myLike = 0
                holder.imgHeart.setImageResource(R.drawable.img_heart)
                holder.imgHeart.setColorFilter(Color.argb(255, 255, 255, 255))
            } else {
                item.myLike = 1
                holder.imgHeart.setImageResource(R.drawable.img_heart_checked)
                holder.imgHeart.setColorFilter(Color.argb(0, 0, 0, 0))
            }
        }

        binding.rcyRootlay.setOnClickListener {
            val intent = Intent(it.context, ProductDetailActivity::class.java)
            intent.putExtra("itemIdx", item.idx)
            ContextCompat.startActivity(it.context, intent, null)
            rootActivity.overridePendingTransition(
                R.anim.horizon_enter_right,
                R.anim.transition_none
            )
        }

        holder.bind(rootActivity, item)
    }

    override fun getItemCount() = productList.size

    override fun onGetDataSuccess(response: HomeRecmndResponse) {
    }

    override fun onGetDataFailure(message: String) {
    }

    override fun onPostFavoritesSuccess(response: FavoritesResponse) {
        Log.d("TEST onPostFavoritesSuccess", response.message)
        Log.d("TEST status", response.result.status.toString())
    }

    override fun onPostFavoritesFailure(message: String) {
        Log.d("TEST onPostFavoritesFailure", message)
    }
}