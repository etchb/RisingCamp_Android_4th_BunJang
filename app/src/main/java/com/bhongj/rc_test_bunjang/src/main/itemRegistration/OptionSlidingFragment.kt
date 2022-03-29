package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.databinding.FragmentOptionSlidingBinding
import com.bhongj.rc_test_bunjang.databinding.FragmentSlidingLoginBinding
import com.bhongj.rc_test_bunjang.src.login.other.RegisterActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionSlidingFragment() : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentOptionSlidingBinding
    private val binding get() = _binding!!

    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentOptionSlidingBinding.inflate(layoutInflater, container, false)

        binding.root.rootView.setBackgroundResource(R.drawable.ripple_background_round)

        return binding.root
    }
}