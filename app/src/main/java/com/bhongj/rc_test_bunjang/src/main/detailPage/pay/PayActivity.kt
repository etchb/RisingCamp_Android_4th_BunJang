package com.bhongj.rc_test_bunjang.src.main.detailPage.pay

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.core.widget.addTextChangedListener
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_PAYMENT_CONTINUE
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.sSharedPreferences
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityPayBinding
import com.bhongj.rc_test_bunjang.src.main.MainActivity
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayRequest
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayResponse
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class PayActivity :
    BaseActivity<ActivityPayBinding>(ActivityPayBinding::inflate), PayActivityInterface {

    lateinit var payRequest: PayRequest
    var isPaymentContinue = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val price = intent.getIntExtra("PAYMENT-PRICE", 0)
        var point = 0
        val t_dec_up = DecimalFormat("#,###")

        isPaymentContinue = sSharedPreferences.getBoolean(MY_PAYMENT_CONTINUE, false)
        if (isPaymentContinue) {
            binding.payCheckContinuePayment.setTextColor(ContextCompat.getColor(this, R.color.black))
            val color = ContextCompat.getColor(this, R.color.bungaeRed)
            val colorList = ColorStateList.valueOf(color)
            TextViewCompat.setCompoundDrawableTintList(binding.payCheckContinuePayment, colorList)
        } else {
            binding.payCheckContinuePayment.setTextColor(ContextCompat.getColor(this, R.color.gray))
            TextViewCompat.setCompoundDrawableTintList(binding.payCheckContinuePayment, null)
        }

        payRequest = PayRequest(
            safetyTax = (price * 0.035).toInt(),
            point = 0,
            totalPaymentAmount = (price * 1.035).toInt(),
            paymentMethod = 1,
            transactionMethod = 1,
            address = "",
        )

        binding.payEdtBungaePoint.addTextChangedListener {
            if (binding.payEdtBungaePoint.text.toString() == "") {
                point = 0
                binding.payPaymentBungaePoint.text = "0???"
                binding.payPaymentPriceTotal.text = t_dec_up.format(payRequest.totalPaymentAmount) + " ???"
            } else {
                point = binding.payEdtBungaePoint.text.toString().toInt()
                binding.payPaymentBungaePoint.text = "-" + t_dec_up.format(point) + "???"
                binding.payPaymentPriceTotal.text = t_dec_up.format(payRequest.totalPaymentAmount - point) + " ???"
            }
        }

        binding.payRdbtnBungae.isChecked = true

        binding.payCheckContinuePayment.setOnClickListener {
            isPaymentContinue = !isPaymentContinue
            if (isPaymentContinue) {
                binding.payCheckContinuePayment.setTextColor(ContextCompat.getColor(this, R.color.black))
                val color = ContextCompat.getColor(this, R.color.bungaeRed)
                val colorList = ColorStateList.valueOf(color)
                TextViewCompat.setCompoundDrawableTintList(binding.payCheckContinuePayment, colorList)
            } else {
                binding.payCheckContinuePayment.setTextColor(ContextCompat.getColor(this, R.color.gray))
                TextViewCompat.setCompoundDrawableTintList(binding.payCheckContinuePayment, null)
            }
        }

        Glide.with(this)
            .load(intent.getStringExtra("PAYMENT-IMG"))
            .into(binding.payImgItem)

        binding.payTxtTitle.text = intent.getStringExtra("PAYMENT-TITLE")
        binding.payPrice.text = t_dec_up.format(price) + "???"

        if (intent.getStringExtra("PAYMENT-TPYE") == "DIRECT") {
            binding.payConlayDelivery.visibility = View.GONE
            binding.payTxtMain.text = "?????????, ???????????????\n???????????????"
            binding.payLinlayIncludeDelivery.visibility = View.GONE
        } else if (intent.getStringExtra("PAYMENT-TPYE") == "PARCEL") {
            binding.payTxtMain.text = "????????????, ???????????????\n???????????????"
            if (intent.getIntExtra("PAYMENT-INCLUDE-DELIVERY", 0) == 0) {
                binding.payTxtIncludeDelivery.text = "???????????????"
            } else {
                binding.payTxtIncludeDelivery.text = "???????????????"
            }
        }

        binding.payPaymentPrice.text = t_dec_up.format(price) + "???"
        binding.payPaymentTax.text = "+" + t_dec_up.format(payRequest.safetyTax) + "???"
        binding.payPaymentPriceTmp.text = t_dec_up.format(payRequest.totalPaymentAmount) + "???"
        binding.payPaymentPriceTotal.text = t_dec_up.format(payRequest.totalPaymentAmount) + " ???"


        binding.payBtnPayment.setOnClickListener {
            if (binding.payCheckbox.isChecked) {
                payRequest.point = point
                payRequest.transactionMethod = 2
                payRequest.address = binding.payEdtAddress.text.toString()

                if (binding.payRdbtnBungae.isChecked) {
                    payRequest.paymentMethod = 0
                } else {
                    payRequest.paymentMethod = 1
                }
                postPayment(intent.getIntExtra("PAYMENT-IDX", 0))
            } else {
                val dlg = DialogMessage(this)
                dlg.setOnOKClickedListener{ content ->
                    // ????????? ????????? ??? ????????? ?????? ?????? ??????
                }
                dlg.start("?????? ??????????????? ??????????????????.")
            }
        }
    }

    override fun onStop() {
        super.onStop()

        val editor = sSharedPreferences.edit()
        editor.putBoolean(MY_PAYMENT_CONTINUE, isPaymentContinue)
        editor.commit()
    }

    fun postPayment(idx: Int) {
        showLoadingDialog(this)
        PayService(this).tryGetData(idx, payRequest)
    }

    override fun onGetDataSuccess(response: PayResponse) {
        dismissLoadingDialog()
        val itemIdx = intent.getIntExtra("PAYMENT-IDX", 0)
        val itemName = intent.getStringExtra("PAYMENT-TITLE")
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("isPaymentOut", true)
        intent.putExtra("itemName", itemName)
        startActivity(intent)
    }

    override fun onGetDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("?????? : $message")
    }
}