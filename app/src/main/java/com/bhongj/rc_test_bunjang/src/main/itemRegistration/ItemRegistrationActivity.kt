package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityItemRegistrationBinding
import com.bhongj.rc_test_bunjang.src.main.MainActivity
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.PostRegistrationRequest
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.RegistrationResponse

class ItemRegistrationActivity :
    BaseActivity<ActivityItemRegistrationBinding>(ActivityItemRegistrationBinding::inflate),
    OptionSlidingFragment.OnDataPass, ItemRegistrationActivityInterface {
    var itemCnt = 1
    var isExchange = false
    var isNew = false
    var includeFee = false
    var isBungaePay = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.regiCheckbox.setOnClickListener {
            includeFee = binding.regiCheckbox.isChecked
        }
        binding.regiLinlayPayCheck.setOnClickListener {
            if (isBungaePay) {
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

        binding.regiBtnRegist.setOnClickListener {
            val imageUrl = listOf("imageUrl1", "imageUrl2")
            val tagName = binding.regiEdtTag.text.toString().split(" ")
            val categoryIdx = 1
            val productName = binding.regiEdtName.text.toString()
            val productDesc = binding.regiEdtDesc.text.toString()
            val productCondition = if (isNew) {
                2
            } else {
                1
            }
            val saftyPay = if (isBungaePay) {
                1
            } else {
                0
            }
            val isExchange = if (isExchange) {
                2
            } else {
                1
            }
            val amount = itemCnt
            val includeFee = if (includeFee) {
                2
            } else {
                1
            }
            val price = if (binding.regiEdtPrice.text.toString() != "") {
                binding.regiEdtPrice.text.toString().toInt()
            } else {
                0
            }
            val directtrans = binding.regiEdtLocation.text.toString()
            postRegistration(
                PostRegistrationRequest(
                    imageUrl = imageUrl,
                    tagName = tagName,
                    categoryIdx = categoryIdx,
                    productName = productName,
                    productDesc = productDesc,
                    productCondition = productCondition,
                    saftyPay = saftyPay,
                    isExchange = isExchange,
                    amount = amount,
                    includeFee = includeFee,
                    price = price,
                    directtrans = directtrans,
                )
            )
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

    fun postRegistration(postRegistrationRequest: PostRegistrationRequest) {
        showLoadingDialog(this)
        ItemRegistrationService(this).tryPostData(postRegistrationRequest)
    }

    override fun onPostDataSuccess(response: RegistrationResponse) {
        dismissLoadingDialog()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("isRegistration", true)
        intent.putExtra("itemName", binding.regiEdtName.text.toString())
        startActivity(intent)
    }

    override fun onPostDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}