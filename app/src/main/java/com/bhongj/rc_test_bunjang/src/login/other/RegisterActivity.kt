package com.bhongj.rc_test_bunjang.src.login.other

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityRegisterBinding
import com.bhongj.rc_test_bunjang.src.login.DesDataList
import java.util.concurrent.TimeUnit

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {
    var btnClickCnt = 0
    var phonCheck = false
    var nameCheck = false
    var registCheck1 = false
    var registCheck2 = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnOtherLoginBack.setOnClickListener {
            onBackPressed()
        }

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
//                binding.txtOtherLoginPhoneAgencyDes.visibility = View.VISIBLE
//                binding.txtOtherLoginPhoneAgency.visibility = View.VISIBLE
                binding.edtOtherLoginPhoneNum.visibility = View.VISIBLE
                binding.txtOtherLoginPhoneNum.visibility = View.VISIBLE
            }
        }

        binding.edtOtherLoginRegistNumRr.addTextChangedListener {
            registCheck2 = binding.edtOtherLoginRegistNumRr.text?.length ?: 0 == 1
            binding.btnOtherLoginNext.isEnabled =
                phonCheck && registCheck1 && registCheck2 && nameCheck
            if (registCheck1 && registCheck2 && (binding.txtOtherLoginPhoneAgencyDes.visibility == View.GONE)) {
//                binding.txtOtherLoginPhoneAgencyDes.visibility = View.VISIBLE
//                binding.txtOtherLoginPhoneAgency.visibility = View.VISIBLE
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
            } else if (btnClickCnt == 1) {
                btnClickCnt++
                binding.edtOtherLoginName.visibility = View.GONE
                binding.txtOtherLoginName.visibility = View.GONE
                binding.edtOtherLoginPhoneNum.visibility = View.GONE
                binding.txtOtherLoginPhoneNum.visibility = View.GONE
                binding.txtOtherLoginPhoneAgencyDes.visibility = View.GONE
                binding.txtOtherLoginPhoneAgency.visibility = View.GONE
                binding.txtOtherLoginRegistNum.visibility = View.GONE
                binding.layOtherLoginRegistNum.visibility = View.GONE
                binding.btnOtherLoginNext.isEnabled = false

                binding.txtOtherLoginMain.text = "인증번호를\n입력해주세요"
                binding.layOtherLoginMainSub.visibility = View.VISIBLE
                binding.txtOtherLoginMainSub.text = binding.edtOtherLoginPhoneNum.text
                binding.txtOtherLoginCertify.visibility = View.VISIBLE
                binding.layOtherLoginCertify.visibility = View.VISIBLE
                binding.edtOtherLoginCertify.requestFocus()
                showKeyboard(binding.edtOtherLoginCertify)

                Thread() {
                    val handler = Handler(Looper.getMainLooper())
                    var seconds : Long = 180
                    var minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
                    var second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60
                    while(true) {
                        Thread.sleep(1000)
                        handler.post {
                            binding.txtOtherLoginCertifyTime.text = "${String.format("%02d", minute)}:${String.format("%02d", second)}"
                        }
                        if (seconds == 0L) {
                            break
                        } else {
                            seconds--
                            minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
                            second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60
                        }
                    }
                }.start()
            }
            else {

            }
        }

        binding.edtOtherLoginCertify.addTextChangedListener {
            binding.btnOtherLoginNext.isEnabled = binding.edtOtherLoginCertify.text?.isNotEmpty() ?: false
        }
    }
}