package com.bhongj.rc_test_bunjang.src.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_test_bunjang.databinding.FragmentSlidingLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SlidingLoginFragment() : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentSlidingLoginBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSlidingLoginBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}