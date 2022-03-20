package com.bhongj.rc_test_bunjang.src.login.other

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.databinding.FragmentSlidingLoginBinding
import com.bhongj.rc_test_bunjang.src.login.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SlidingLoginFragment() : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentSlidingLoginBinding
    private val binding get() = _binding!!

    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSlidingLoginBinding.inflate(layoutInflater, container, false)

        binding.root.rootView.setBackgroundResource(R.drawable.ripple_background_round)

        binding.btnLoginPhone.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
            this.dismiss()
        }

        return binding.root
    }
}