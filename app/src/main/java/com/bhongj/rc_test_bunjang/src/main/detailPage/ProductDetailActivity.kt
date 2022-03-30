package com.bhongj.rc_test_bunjang.src.main.detailPage

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_IDX
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityProductDetailBinding
import com.bhongj.rc_test_bunjang.src.main.MainActivity
import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DeleteResponse
import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DetailResponse
import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DetailResult
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.SlidingPayFragment
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayPageData
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.ItemRegistrationActivity
import com.bumptech.glide.Glide
import me.relex.circleindicator.CircleIndicator3
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.min

class ProductDetailActivity :
    BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate),
    DetailActivityInterface {

    val DetailItemImg = mutableListOf<String>()
    lateinit var pagerAdapter: ItemSlidePagerAdapter
    lateinit var mIndicator: CircleIndicator3
    var itemIdx = 0
    lateinit var result: DetailResult

    val payPageData = PayPageData(
        idx = 0,
        img = "",
        title = "",
        price = 0,
        includeDelivery = 0,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemIdx = intent.getIntExtra("itemIdx", 0)
        getDeatilData(itemIdx)
        payPageData.idx = itemIdx

        binding.detilBtnDelete.setOnClickListener {
            patchDeleteItem(itemIdx)
        }

        binding.detilBtnUpdate.setOnClickListener {
            val intent = Intent(this, ItemRegistrationActivity::class.java)
            intent.putExtra("U_itemIdx", itemIdx)
            intent.putExtra("U_isUpdate", true)
            intent.putExtra("U_itemCnt", result.amount) //int
            intent.putExtra("U_isExchange", false) //
            intent.putExtra("U_isNew", result.productCondition-1) //int
            intent.putExtra("U_includeFee", result.includeFee-1) //int
            intent.putExtra("U_isBungaePay", result.saftyPay) //int
            intent.putExtra("U_itemName", result.productName) //string
            intent.putExtra("U_category", result.categoryName) //string
            intent.putExtra("U_tag", result.tag) //string
            intent.putExtra("U_price", result.price) //int
            intent.putExtra("U_content", result.productDesc) //string
            intent.putExtra("U_location", result.directtrans) //string
            startActivity(intent)
        }

        binding.detailBtnPayment.setOnClickListener {
            val slidingPayFragment = SlidingPayFragment(payPageData)
            slidingPayFragment.show(supportFragmentManager, slidingPayFragment.tag)
        }

        binding.detailScrMain.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            val verticalOffset = i2

            val alpha = min(abs(verticalOffset / 4), 255)
            binding.tlbDetail.setBackgroundColor(Color.argb(alpha, 255, 255, 255))
            binding.detailTxtInfo.setTextColor(Color.argb(alpha, 0, 0, 0))
            if (alpha > 255 / 2) {
                binding.tlbBtnHomeMenu.setColorFilter(Color.rgb(0, 0, 0))
                binding.tlbBtnHomeSearch.setColorFilter(Color.rgb(0, 0, 0))
                binding.tlbBtnHomeShare.setColorFilter(Color.rgb(0, 0, 0))
            } else {
                binding.tlbBtnHomeMenu.setColorFilter(Color.rgb(255, 255, 255))
                binding.tlbBtnHomeSearch.setColorFilter(Color.rgb(255, 255, 255))
                binding.tlbBtnHomeShare.setColorFilter(Color.rgb(255, 255, 255))
            }
            if (verticalOffset / 4 > 350) {
                if (binding.detailConlaySmmryInfo.visibility == View.GONE) {
                    binding.detailConlaySmmryInfo.visibility = View.VISIBLE
                }
            } else {
                if (binding.detailConlaySmmryInfo.visibility == View.VISIBLE) {
                    binding.detailConlaySmmryInfo.visibility = View.GONE
                }
            }
        }
    }

    inner class ItemSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = DetailItemImg.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                in 0 until this.itemCount -> {
                    DetailItemSlideFragment(DetailItemImg[position])
                }
                else -> DetailItemSlideFragment(DetailItemImg[0])
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.transition_none, R.anim.horizon_exit_right)
    }

    fun getDeatilData(idx: Int) {
        showLoadingDialog(this)
        if (idx == 0) {
            onBackPressed()
        } else {
            DetailService(this).tryGetData(idx)
        }
    }

    fun patchDeleteItem(idx: Int) {
        showLoadingDialog(this)
        if (idx == 0) {
            onBackPressed()
        } else {
            DetailService(this).tryPatchData(idx)
        }
    }

    override fun onGetDataSuccess(response: DetailResponse) {
        dismissLoadingDialog()
        result = response.result

        val t_dec_up = DecimalFormat("#,###")
        t_dec_up.format(result.price)
        payPageData.price = result.price
        binding.detailTxtPrice.text = t_dec_up.format(result.price).toString() + "원"
        binding.detailTxtTlbPrice.text = t_dec_up.format(result.price).toString() + "원"
        if (result.saftyPay == 1) {
            binding.detailImgBungae.visibility = View.VISIBLE
        } else {
            binding.detailImgBungae.visibility = View.INVISIBLE
        }
        binding.detailTxtTitle.text = result.productName
        binding.detailTxtTlbTitle.text = result.productName
        payPageData.title = result.productName
        binding.detailTxtTime.text
        binding.detailTxtEye.text = result.viewCount.toString()
        binding.detailTxtHeart.text = result.likeCount.toString()
        binding.detailTxtRegion.text = result.directtrans
        var tmpStr = ""
        if (result.productCondition == 0) {
            tmpStr = "중고"
        } else {
            tmpStr = "새상품"
        }
        tmpStr = "$tmpStr • "
        if (result.includeFee == 0) {
            tmpStr += "배송비별도"
            binding.detailTxtTlbShopDelivery.text = "배송비별도"
            payPageData.includeDelivery = 0
        } else {
            tmpStr += "배송비포함"
            binding.detailTxtTlbShopDelivery.text = "배송비포함"
            payPageData.includeDelivery = 1
        }
        tmpStr = tmpStr + " • " + "총 ${result.amount}개"
        binding.detailTxtDelivery.text = tmpStr
        binding.detailTxtBody.text = result.productDesc
        binding.detailTxtCategory.text = result.categoryName
        binding.detailTxtShopName.text = result.shopName
        binding.detailTxtTlbShopName.text = result.shopName
        binding.detailTxtShopFollowerCnt.text = result.follower.toString()
        binding.detailTxtTlbShopRating.text = "⭐ 5.0(${result.follower.toString()})"
        binding.viewWhite.visibility = View.GONE

        var myLike = result.myLike
        if (result.myLike == 0) {
            binding.detailMyHeart.setImageResource(R.drawable.img_detail_btm_heart)
        } else {
            binding.detailMyHeart.setImageResource(R.drawable.img_detail_btm_heart_checked)
        }

        binding.detailMyHeart.setOnClickListener {
            if (myLike == 0) {
                myLike = 1
                binding.detailMyHeart.setImageResource(R.drawable.img_detail_btm_heart)
            } else {
                myLike = 0
                binding.detailMyHeart.setImageResource(R.drawable.img_detail_btm_heart_checked)
            }
        }

        val imgUrl = result.imageUrl.split(",")
        DetailItemImg.addAll(imgUrl)

        payPageData.img = imgUrl[0]
        Glide.with(this)
            .load(imgUrl[0])
            .into(binding.detailImgTlbItem)

        pagerAdapter = ItemSlidePagerAdapter(this)
        val mPager = binding.detailVpMainItem
        mPager.adapter = pagerAdapter
        mPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        mIndicator = binding.vpDesIndi
        mIndicator.setViewPager(mPager)
        mIndicator.createIndicators(pagerAdapter.itemCount, 0)
        mPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (positionOffsetPixels == 0) {
                    mPager.currentItem = position
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mIndicator.animatePageSelected(position % pagerAdapter.itemCount)
            }
        })

        if (result.uidx == ApplicationClass.sSharedPreferences.getInt(MY_IDX, 0)) {
            binding.linlayOwn.visibility = View.VISIBLE
        }
    }

    override fun onGetDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPatchSuccess(response: DeleteResponse) {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("isDelete", true)
        intent.putExtra("itemIdx", itemIdx)
        startActivity(intent)
    }

    override fun onPatchFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}
