package com.bhongj.rc_test_bunjang.src.main.detail

import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentAdSlideBinding
import com.bhongj.rc_test_bunjang.databinding.FragmentDetailItemSlideBinding

class DetailItemSlideFragment(val image: Int) :
    BaseFragment<FragmentDetailItemSlideBinding>(FragmentDetailItemSlideBinding::bind, R.layout.fragment_detail_item_slide) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgDetailItemSlide.setImageResource(image)
    }
}