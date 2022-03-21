package com.bhongj.rc_test_bunjang.src.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentHomeBinding
import com.bhongj.rc_test_bunjang.src.login.DesDataList
import com.bhongj.rc_test_bunjang.src.main.home.models.SignUpResponse
import com.bhongj.rc_test_bunjang.src.main.home.models.UserResponse

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentInterface {

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

        val pagerAdapter = AdSlidePagerAdapter(requireActivity())
        val mPager = binding.vpHomeAd
        mPager.adapter = pagerAdapter
        mPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.txtHomeAdIdx.text = "${binding.vpHomeAd.currentItem+1}/${AdResourseData.size}"
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
    }

    override fun onGetUserSuccess(response: UserResponse) {
        dismissLoadingDialog()
        for (User in response.result) {
            Log.d("HomeFragment", User.toString())
        }
//        binding.homeButtonTryGetJwt.text = response.message
//        showCustomToast("Get JWT 성공")
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

    override fun onGetUserFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
//        binding.homeBtnTryPostHttpMethod.text = response.message
//        response.message?.let { showCustomToast(it) }
    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}