package com.bhongj.rc_test_bunjang.src.main.detailPage

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityProductDetailBinding
import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DetailResponse
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.SlidingPayFragment
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayPageData
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayRequest
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

    val payPageData = PayPageData(
        idx = 0,
        img = "",
        title = "",
        price = 0,
        includeDelivery = 0,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idx = intent.getIntExtra("itemIdx", 0)
        getDeatilData(idx)
        payPageData.idx = idx

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

    override fun onGetDataSuccess(response: DetailResponse) {
        dismissLoadingDialog()
        val result = response.result

        val t_dec_up = DecimalFormat("#,###")
        t_dec_up.format(result.price)
        payPageData.price = result.price
        binding.detailTxtPrice.text = t_dec_up.format(result.price).toString() + "원"
        if (result.saftyPay == 1) {
            binding.detailImgBungae.visibility = View.VISIBLE
        } else {
            binding.detailImgBungae.visibility = View.INVISIBLE
        }
        binding.detailTxtTitle.text = result.productName
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
            payPageData.includeDelivery = 0
        } else {
            tmpStr += "배송비포함"
            payPageData.includeDelivery = 1
        }
        tmpStr = tmpStr + " • " + "총 ${result.amount}개"
        binding.detailTxtDelivery.text = tmpStr
        binding.detailTxtBody.text = result.productDesc
        binding.detailTxtCategory.text = result.categoryName
        binding.detailTxtShopName.text = result.shopName
        binding.detailTxtShopFollowerCnt.text = result.follower.toString()
        binding.viewWhite.visibility = View.GONE

        val imgUrl = result.imageUrl.split(",")
        DetailItemImg.addAll(imgUrl)
        payPageData.img = imgUrl[0]

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
    }

    override fun onGetDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}
