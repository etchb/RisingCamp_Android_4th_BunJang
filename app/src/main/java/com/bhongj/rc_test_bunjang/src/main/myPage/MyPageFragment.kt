package com.bhongj.rc_test_bunjang.src.main.myPage

import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentMyPageBinding

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    private var mCount = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChangeCounterText.setOnClickListener {
            binding.textViewCounter.text =
                resources.getString(R.string.my_page_tv_counter, ++mCount)
        }
    }
}