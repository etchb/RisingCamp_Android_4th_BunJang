package com.bhongj.rc_test_bunjang.src.login

import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentDesSlideBinding

class DesSlideFragment(val desData: DesData) :
    BaseFragment<FragmentDesSlideBinding>(
        FragmentDesSlideBinding::bind,
        R.layout.fragment_des_slide
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtDesTitle.setText(desData.TITLE)
        binding.txtDesContent.setText(desData.CONTENT)
        binding.imgDesSlide.setImageResource(desData.RES)
    }
}