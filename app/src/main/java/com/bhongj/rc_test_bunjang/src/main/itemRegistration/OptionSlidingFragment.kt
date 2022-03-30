package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.databinding.FragmentOptionSlidingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionSlidingFragment(var itemCnt: Int, var isExchange: Boolean, var isNew: Boolean) : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentOptionSlidingBinding
    private val binding get() = _binding!!
    lateinit var dataPasser: OnDataPass

    override fun getTheme() = R.style.NoBackgroundDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentOptionSlidingBinding.inflate(layoutInflater, container, false)

        binding.root.rootView.setBackgroundResource(R.drawable.ripple_background_round)

        if (itemCnt > 0) {
            binding.optionEdtItemCnt.setText(itemCnt.toString())
        }

        if (isExchange) {
            binding.optionBtnExchangeOk.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnExchangeOk.setBackgroundResource(R.drawable.option_light_red)
        } else {
            binding.optionBtnExchangeNo.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnExchangeNo.setBackgroundResource(R.drawable.option_light_red)
        }

        if (isNew) {
            binding.optionBtnItemNew.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnItemNew.setBackgroundResource(R.drawable.option_light_red)
        } else {
            binding.optionBtnItemOld.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnItemOld.setBackgroundResource(R.drawable.option_light_red)
        }

        binding.optionBtnItemOld.setOnClickListener {
            isNew = false
            binding.optionBtnItemOld.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnItemOld.setBackgroundResource(R.drawable.option_light_red)

            binding.optionBtnItemNew.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.optionBtnItemNew.setBackgroundResource(R.drawable.option_white_border)
        }

        binding.optionBtnItemNew.setOnClickListener {
            isNew = true
            binding.optionBtnItemNew.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnItemNew.setBackgroundResource(R.drawable.option_light_red)

            binding.optionBtnItemOld.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.optionBtnItemOld.setBackgroundResource(R.drawable.option_white_border)
        }

        binding.optionBtnExchangeNo.setOnClickListener {
            isExchange = false
            binding.optionBtnExchangeNo.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnExchangeNo.setBackgroundResource(R.drawable.option_light_red)

            binding.optionBtnExchangeOk.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.optionBtnExchangeOk.setBackgroundResource(R.drawable.option_white_border)
        }

        binding.optionBtnExchangeOk.setOnClickListener {
            isExchange = true
            binding.optionBtnExchangeOk.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.bungaeRed))
            binding.optionBtnExchangeOk.setBackgroundResource(R.drawable.option_light_red)

            binding.optionBtnExchangeNo.setTextColor(ContextCompat.getColor(this.requireContext(), R.color.gray))
            binding.optionBtnExchangeNo.setBackgroundResource(R.drawable.option_white_border)
        }

        binding.optionBtnDone.setOnClickListener {
            itemCnt = if (binding.optionEdtItemCnt.text.toString() != "") {
                binding.optionEdtItemCnt.text.toString().toInt()
            } else {
                0
            }
            passData(itemCnt, isExchange, isNew)
            this.dismiss()
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    fun passData(itemCnt: Int, isExchange: Boolean, isNew: Boolean){
        dataPasser.onDataPass(itemCnt, isExchange, isNew)
    }
    
    interface OnDataPass {
        fun onDataPass(itemCnt: Int, isExchange: Boolean, isNew: Boolean )
    }
}