package com.bhongj.rc_test_bunjang.src.main.detailPage.pay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.databinding.FragmentSlidingPayBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SlidingPayFragment() : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentSlidingPayBinding
    private val binding get() = _binding!!

    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSlidingPayBinding.inflate(layoutInflater, container, false)

        binding.root.rootView.setBackgroundResource(R.drawable.ripple_background_round)

//        binding.btnLoginPhone.setOnClickListener {
//            val intent = Intent(requireContext(), RegisterActivity::class.java)
//            startActivity(intent)
//            this.dismiss()
//        }

        return binding.root
    }
}