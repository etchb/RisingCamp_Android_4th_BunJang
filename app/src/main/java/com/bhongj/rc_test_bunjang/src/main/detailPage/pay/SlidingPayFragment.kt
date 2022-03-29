package com.bhongj.rc_test_bunjang.src.main.detailPage.pay

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.databinding.FragmentSlidingPayBinding
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayPageData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SlidingPayFragment(val payPageData: PayPageData) : BottomSheetDialogFragment() {
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

        binding.linlayDirect.setOnClickListener {
            val intent = Intent(requireContext(), PayActivity::class.java)
            intent.putExtra("PAYMENT-TPYE", "DIRECT")
            intent.putExtra("PAYMENT-IDX", payPageData.idx)
            intent.putExtra("PAYMENT-TITLE", payPageData.title)
            intent.putExtra("PAYMENT-IMG", payPageData.img)
            intent.putExtra("PAYMENT-PRICE", payPageData.price)
            intent.putExtra("PAYMENT-INCLUDE-DELIVERY", payPageData.includeDelivery)
            startActivity(intent)
            this.dismiss()
        }

        binding.linlayParcel.setOnClickListener {
            val intent = Intent(requireContext(), PayActivity::class.java)
            intent.putExtra("PAYMENT-TPYE", "PARCEL")
            intent.putExtra("PAYMENT-IDX", payPageData.idx)
            intent.putExtra("PAYMENT-TITLE", payPageData.title)
            intent.putExtra("PAYMENT-IMG", payPageData.img)
            intent.putExtra("PAYMENT-PRICE", payPageData.price)
            intent.putExtra("PAYMENT-INCLUDE-DELIVERY", payPageData.includeDelivery)
            startActivity(intent)
            this.dismiss()
        }

        return binding.root
    }
}