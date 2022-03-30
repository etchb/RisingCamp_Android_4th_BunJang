package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityItemRegistrationBinding
import com.bhongj.rc_test_bunjang.src.login.other.SlidingLoginFragment

class ItemRegistrationActivity :
    BaseActivity<ActivityItemRegistrationBinding>(ActivityItemRegistrationBinding::inflate),
    OptionSlidingFragment.OnDataPass {
    var itemCnt = 0
    var isExchange = false
    var isNew = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isBungaePay = false

        binding.regiLinlayPayCheck.setOnClickListener {
            Log.d("TEST isBungaePay", isBungaePay.toString())
            if(isBungaePay) {
                isBungaePay = false
                binding.regiImgBungaePayCheck.setImageResource(R.drawable.pay_check)
                binding.regiLinlayPayCheck.setBackgroundResource(R.drawable.ripple_item_regi_textbox_border)
                binding.regiTxtBungaePay.setTextColor(Color.GRAY)
            } else {
                isBungaePay = true
                binding.regiImgBungaePayCheck.setImageResource(R.drawable.pay_checked)
                binding.regiLinlayPayCheck.setBackgroundResource(R.drawable.ripple_item_regi_textbox_border_checked)
                binding.regiTxtBungaePay.setTextColor(Color.BLACK)
            }
        }

        binding.regiTxtMore.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.regiBtnOption.setOnClickListener {
            val optionSlidingFragment = OptionSlidingFragment(itemCnt, isExchange, isNew)
            optionSlidingFragment.show(supportFragmentManager, optionSlidingFragment.tag)

        }
    }

    override fun onDataPass(itemCnt: Int, isExchange: Boolean, isNew: Boolean) {
        this.itemCnt = itemCnt
        this.isExchange = isExchange
        this.isNew = isNew

        var tmpStr = ""
        tmpStr = itemCnt.toString() + "개 • "
        tmpStr += if (isNew) {
            "새상품 • "
        } else {
            "중고상품 • "
        }
        tmpStr += if (isExchange) {
            "교환가능"
        } else {
            "교환불가"
        }
        binding.regiTxtOption.text = tmpStr
    }
}