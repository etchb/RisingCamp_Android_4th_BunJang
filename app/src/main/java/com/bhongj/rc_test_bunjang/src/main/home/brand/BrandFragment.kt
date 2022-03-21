package com.bhongj.rc_test_bunjang.src.main.home.brand

import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentBrandBinding

class BrandFragment :
    BaseFragment<FragmentBrandBinding>(FragmentBrandBinding::bind, R.layout.fragment_brand) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}