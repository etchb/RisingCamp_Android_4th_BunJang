package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityItemRegistrationBinding
import com.bhongj.rc_test_bunjang.src.main.MainActivity
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.PostRegistrationRequest
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.RegistrationResponse
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.UpdateDataResponse

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

        if (intent.getBooleanExtra("U_isUpdate", false)) {
            binding.regiEdtName.setText(intent.getStringExtra("U_itemName"))
            binding.regiEdtCategory.setText(intent.getStringExtra("U_category"))
            binding.regiEdtPrice.setText(intent.getIntExtra("U_price", 0).toString())
            binding.regiEdtDesc.setText(intent.getStringExtra("U_content"))
            binding.regiEdtLocation.setText(intent.getStringExtra("U_location"))

            isExchange = intent.getIntExtra("U_isExchange", 0) > 0
            isNew = intent.getIntExtra("U_isNew", 0) > 0
            includeFee = intent.getIntExtra("U_includeFee", 0) > 0
            binding.regiCheckbox.isChecked = includeFee
            isBungaePay = intent.getIntExtra("U_isBungaePay", 0) > 0
            if (isBungaePay) {
                binding.regiImgBungaePayCheck.setImageResource(R.drawable.pay_checked)
                binding.regiLinlayPayCheck.setBackgroundResource(R.drawable.ripple_item_regi_textbox_border_checked)
                binding.regiTxtBungaePay.setTextColor(Color.BLACK)
            }
            itemCnt = intent.getIntExtra("U_itemCnt", 0)
            var tmpStr = ""
            for (tmp in intent.getStringExtra("U_tag")?.split(",")!!) {
                tmpStr += "$tmp "
            }
            tmpStr = tmpStr.trimStart()
            tmpStr = tmpStr.trimEnd()

            binding.regiEdtTag.setText(tmpStr)
            tmpStr = ""
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
            var tmpStr = binding.regiEdtTag.text.toString()
            tmpStr = tmpStr.trimStart()
            tmpStr = tmpStr.trimEnd()
            val tagName = tmpStr.split(" ")
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
            if (intent.getBooleanExtra("U_isUpdate", false)) {
                Log.d("TEST U_itemIdx", intent.getIntExtra("U_itemIdx", 0).toString())
                Log.d("TEST PostRegistrationRequest", PostRegistrationRequest(
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
                    ).toString())
                patchUpdate(
                    intent.getIntExtra("U_itemIdx", 0),
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

            } else {
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

    fun patchUpdate(itemIdx: Int, postRegistrationRequest: PostRegistrationRequest) {
        showLoadingDialog(this)
        ItemRegistrationService(this).tryPatchUpdateData(itemIdx, postRegistrationRequest)
    }

    override fun onPostDataSuccess(response: RegistrationResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("isRegistration", true)
            intent.putExtra("itemName", binding.regiEdtName.text.toString())
            startActivity(intent)
        } else {
            showCustomToast("등록에 실패했습니다. 다시 시도하세요.")
        }
    }

    override fun onPostDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPatchUpdateSuccess(response: UpdateDataResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("isUpdate", true)
            intent.putExtra("itemName", binding.regiEdtName.text.toString())
            startActivity(intent)
        } else {
            showCustomToast("수정에 실패했습니다. 다시 시도하세요.")
        }
    }

    override fun onPatchUpdateFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}

