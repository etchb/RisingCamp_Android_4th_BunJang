package com.bhongj.rc_test_bunjang.src.main.home

import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentAdSlideBinding

class AdSlideFragment(val image: Int) :
    BaseFragment<FragmentAdSlideBinding>(FragmentAdSlideBinding::bind, R.layout.fragment_ad_slide) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgAdSlide.setImageResource(image)
    }
}