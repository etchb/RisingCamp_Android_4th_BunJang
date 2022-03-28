package com.bhongj.rc_test_bunjang.src.main.home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentHomeBinding
import com.bhongj.rc_test_bunjang.src.main.home.brand.BrandFragment
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.RecmndFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs
import kotlin.math.min


class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    val AdResourseData = mutableListOf(
        R.drawable.img_home_ad1,
        R.drawable.img_home_ad2,
        R.drawable.img_home_ad3,
        R.drawable.img_home_ad4,
        R.drawable.img_home_ad5,
        R.drawable.img_home_ad6,
        R.drawable.img_home_ad7,
        R.drawable.img_home_ad8,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adPageCnt = 0
        var pageChanged = false

        binding.scrSubCategory.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            val maxWidthScroll =
                binding.scrSubCategory.getChildAt(0).width - binding.scrSubCategory.width
            if (maxWidthScroll > 0) {
                val pos: Float = i / maxWidthScroll.toFloat()
                val set = ConstraintSet()
                set.clone(binding.homeConlayBody)
                set.setHorizontalBias(R.id.scrollbar_front, pos)
                set.applyTo(binding.homeConlayBody)
            }
        }

        val pagerAdapter = AdSlidePagerAdapter(requireActivity())
        val mPager = binding.vpHomeAd
        mPager.adapter = pagerAdapter
        mPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.txtHomeAdIdx.text =
                    "${binding.vpHomeAd.currentItem + 1}/${AdResourseData.size}"
                adPageCnt = binding.vpHomeAd.currentItem
                pageChanged = true
                super.onPageSelected(position)
            }
        })

        Thread() {
            val handler = Handler(Looper.getMainLooper())
            while (true) {
                Thread.sleep(2000)
                if (pageChanged) {
                    pageChanged = false
                    continue
                }
                handler.post {
                    binding.vpHomeAd.setCurrentItem(++adPageCnt % AdResourseData.size, false)
                }
            }
        }.start()

        binding.appbarlayHome.addOnOffsetChangedListener(object :
            AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                val alpha = min(abs(verticalOffset / 3), 255)
                binding.tlbHome.setBackgroundColor(Color.argb(alpha, 255, 255, 255))
                if (alpha > 255 / 2) {
                    binding.tlbBtnHomeMenu.setColorFilter(Color.rgb(0, 0, 0))
                    binding.tlbBtnHomeSearch.setColorFilter(Color.rgb(0, 0, 0))
                    binding.tlbBtnHomeNoti.setColorFilter(Color.rgb(0, 0, 0))
                } else {
                    binding.tlbBtnHomeMenu.setColorFilter(Color.rgb(255, 255, 255))
                    binding.tlbBtnHomeSearch.setColorFilter(Color.rgb(255, 255, 255))
                    binding.tlbBtnHomeNoti.setColorFilter(Color.rgb(255, 255, 255))
                }
            }
        })

        val pagerAdapterProduct = ProductPagerAdapter(requireActivity())
        val mPagerProduct = binding.vpHomeProduct
        mPagerProduct.adapter = pagerAdapterProduct
        mPagerProduct.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mPagerProduct.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0 && binding.scrBrandTab.visibility == View.VISIBLE) {
                    binding.scrBrandTab.visibility = View.GONE
                } else if (position == 1 && binding.scrBrandTab.visibility == View.GONE) {
                    binding.scrBrandTab.visibility = View.VISIBLE
                }
            }
        })

        val tabTitleArray = arrayOf(
            "  추천상품  ",
            "  브랜드\uD83D\uDD34  ",
        )

        TabLayoutMediator(binding.tablayHomeProduct, binding.vpHomeProduct) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

    }

    private inner class AdSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = AdResourseData.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                in 0 until this.itemCount -> {
                    AdSlideFragment(AdResourseData[position])
                }
                else -> AdSlideFragment(R.drawable.img_home_ad1)
            }
        }
    }

    private inner class ProductPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        val fragmentList = listOf<Fragment>(RecmndFragment(), BrandFragment())

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}