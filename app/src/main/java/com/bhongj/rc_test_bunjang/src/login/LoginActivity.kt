package com.bhongj.rc_test_bunjang.src.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagerAdapter = DesSlidePagerAdapter(this)
        val mPager = binding.vpDes
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

        binding.btnOtherLogin.setOnClickListener {
            val slidingLoginFragment = SlidingLoginFragment()
            slidingLoginFragment.show(supportFragmentManager, slidingLoginFragment.tag)
        }

        Thread() {
            var cnt = 0
            val handler = Handler(Looper.getMainLooper())
            while (true) {
                Thread.sleep(2000)
                handler.post {
                    binding.vpDes.setCurrentItem(++cnt % DesDataList.size, false)
                }
            }
        }.start()
    }

    private inner class DesSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = DesDataList.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                in 0 until this.itemCount -> {
                    DesSlideFragment(DesDataList[position])
                }
                else -> DesSlideFragment(DesDataList[0])
            }
        }
    }
}