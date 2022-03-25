package com.bhongj.rc_test_bunjang.src.main.detail

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
import kotlin.math.abs
import kotlin.math.min

class ProductDetailActivity :
    BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate) {

    val DetailItemImg = mutableListOf(
        R.drawable.img_home_ad5,
        R.drawable.img_home_ad6,
        R.drawable.img_home_ad7,
        R.drawable.img_home_ad8,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagerAdapter = ItemSlidePagerAdapter(this)
        val mPager = binding.detailVpMainItem
        mPager.adapter = pagerAdapter
        mPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val mIndicator = binding.vpDesIndi
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

    private inner class ItemSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
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
}
