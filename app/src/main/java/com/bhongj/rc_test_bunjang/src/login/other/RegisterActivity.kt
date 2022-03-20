package com.bhongj.rc_test_bunjang.src.login.other

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {
    var btnClickCnt = 0
    var phonCheck = false
    var nameCheck = false
    var registCheck1 = false
    var registCheck2 = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.txtOtherLoginMain.text = "이름을\n입력해주세요"

        binding.edtOtherLoginPhoneNum.visibility = View.GONE
        binding.txtOtherLoginPhoneNum.visibility = View.GONE
        binding.txtOtherLoginPhoneAgencyDes.visibility = View.GONE
        binding.txtOtherLoginPhoneAgency.visibility = View.GONE
        binding.txtOtherLoginRegistNum.visibility = View.GONE
        binding.layOtherLoginRegistNum.visibility = View.GONE

        binding.edtOtherLoginName.requestFocus()

        showKeyboard(binding.edtOtherLoginName)

        binding.edtOtherLoginName.addTextChangedListener {
            nameCheck = binding.edtOtherLoginName.text?.length ?: 0 > 0
            binding.btnOtherLoginNext.isEnabled =
                phonCheck && registCheck1 && registCheck2 && nameCheck
            if (btnClickCnt == 0) {
                binding.btnOtherLoginNext.isEnabled =
                    binding.edtOtherLoginName.text?.length ?: 0 > 0
            }
        }

        binding.edtOtherLoginRegistNumFr.addTextChangedListener {
            registCheck1 = binding.edtOtherLoginRegistNumFr.text?.length ?: 0 == 6
            if (registCheck1) {
                binding.edtOtherLoginRegistNumRr.requestFocus()
            }
            binding.btnOtherLoginNext.isEnabled =
                phonCheck && registCheck1 && registCheck2 && nameCheck
            if (registCheck1 && registCheck2 && (binding.txtOtherLoginPhoneAgencyDes.visibility == View.GONE)) {
                binding.txtOtherLoginPhoneAgencyDes.visibility = View.VISIBLE
                binding.txtOtherLoginPhoneAgency.visibility = View.VISIBLE
                binding.edtOtherLoginPhoneNum.visibility = View.VISIBLE
                binding.txtOtherLoginPhoneNum.visibility = View.VISIBLE
            }
        }

        binding.edtOtherLoginRegistNumRr.addTextChangedListener {
            registCheck2 = binding.edtOtherLoginRegistNumRr.text?.length ?: 0 == 1
            binding.btnOtherLoginNext.isEnabled =
                phonCheck && registCheck1 && registCheck2 && nameCheck
            if (registCheck1 && registCheck2 && (binding.txtOtherLoginPhoneAgencyDes.visibility == View.GONE)) {
                binding.txtOtherLoginPhoneAgencyDes.visibility = View.VISIBLE
                binding.txtOtherLoginPhoneAgency.visibility = View.VISIBLE
                binding.edtOtherLoginPhoneNum.visibility = View.VISIBLE
                binding.txtOtherLoginPhoneNum.visibility = View.VISIBLE

                binding.txtOtherLoginMain.text = "휴대폰번호를\n입력해주세요"
                binding.edtOtherLoginPhoneNum.requestFocus()
            }
        }

        binding.edtOtherLoginPhoneNum.addTextChangedListener {
            phonCheck = binding.edtOtherLoginPhoneNum.text?.length ?: 0 == 11
            if (phonCheck) {
                binding.txtOtherLoginMain.text = "입력한 정보를\n확인해주세요"
                hideKeyboard()
            }
            binding.btnOtherLoginNext.isEnabled =
                phonCheck && registCheck1 && registCheck2 && nameCheck
        }

        binding.btnOtherLoginNext.setOnClickListener {
            if (btnClickCnt == 0) {
                btnClickCnt++

                binding.txtOtherLoginMain.text = "생년월일을\n입력해주세요"

                binding.txtOtherLoginRegistNum.visibility = View.VISIBLE
                binding.layOtherLoginRegistNum.visibility = View.VISIBLE
                binding.edtOtherLoginRegistNumFr.requestFocus()

                binding.btnOtherLoginNext.isEnabled = false
            }
        }
    }
}