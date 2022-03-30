package com.bhongj.rc_test_bunjang.src.login.other

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_BIRTH
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_IDX
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_NAME
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_PASSWORD
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_PHONE_NUMBER
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_SEX
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_SHOP_NAME
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.sSharedPreferences
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityRegisterBinding
import com.bhongj.rc_test_bunjang.src.login.other.models.LoginResponse
import com.bhongj.rc_test_bunjang.src.login.other.models.PostLoginRequest
import com.bhongj.rc_test_bunjang.src.login.other.models.PostSignUpRequest
import com.bhongj.rc_test_bunjang.src.login.other.models.SignUpResponse
import com.bhongj.rc_test_bunjang.src.main.MainActivity
import java.util.concurrent.TimeUnit

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate),
    OtherLoginActivityInterface {
    var btnClickCnt = 0
    var phonCheck = false
    var nameCheck = false
    var registCheck1 = false
    var registCheck2 = false

    val handler = Handler(Looper.getMainLooper())

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
                    var seconds: Long = 180
                    var minute =
                        TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
                    var second =
                        TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60
                    while (true) {
                        Thread.sleep(1000)
                        handler.post {
                            binding.txtOtherLoginCertifyTime.text =
                                "${String.format("%02d", minute)}:${String.format("%02d", second)}"
                        }
                        if (seconds == 0L) {
                            break
                        } else {
                            seconds--
                            minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(
                                seconds
                            ) * 60
                            second =
                                TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(
                                    seconds
                                ) * 60
                        }
                    }
                }.start()
            } else if (btnClickCnt == 2) {
                btnClickCnt++
//                showLoadingDialog(this)
                tryLogin()
            } else if (btnClickCnt == 3) {
                btnClickCnt++
//                showLoadingDialog(this)
                trySignUp()
            }
        }

        binding.edtOtherLoginCertify.addTextChangedListener {
            binding.btnOtherLoginNext.isEnabled =
                binding.edtOtherLoginCertify.text?.isNotEmpty() ?: false
        }

        binding.edtOtherLoginShopName.addTextChangedListener {
            binding.btnOtherLoginNext.isEnabled =
                binding.edtOtherLoginCertify.text?.isNotEmpty() ?: false
        }
    }

    fun tryLogin() {
        OtherLoginService(this).tryPostLogin(
            PostLoginRequest(
                phoneNumber = binding.edtOtherLoginPhoneNum.text.toString(),
                userName = binding.edtOtherLoginName.text.toString(),
                userBirth = binding.edtOtherLoginRegistNumFr.text.toString(),
                userPwd = binding.edtOtherLoginCertify.text.toString()
            )
        )
    }

    fun trySignUp() {
        OtherLoginService(this).tryPostSignUp(
            PostSignUpRequest(
                shopName = binding.edtOtherLoginShopName.text.toString(),
                phoneNumber = binding.edtOtherLoginPhoneNum.text.toString(),
                userName = binding.edtOtherLoginName.text.toString(),
                userBirth = binding.edtOtherLoginRegistNumFr.text.toString(),
                userPwd = binding.edtOtherLoginCertify.text.toString()
            )
        )
    }

    fun editShopName() {
        binding.txtOtherLoginMain.text = "마지막 단계입니다!\n상점명을 입력해주세요"
        binding.layOtherLoginMainSub.visibility = View.INVISIBLE
        binding.txtOtherLoginCertify.visibility = View.GONE
        binding.layOtherLoginCertify.visibility = View.GONE
        binding.btnOtherLoginNext.isEnabled = false

        binding.edtOtherLoginShopName.visibility = View.VISIBLE
        binding.txtOtherLoginShopNameDes.visibility = View.VISIBLE
        binding.txtOtherLoginShopName.visibility = View.VISIBLE
        binding.edtOtherLoginShopName.requestFocus()
        showKeyboard(binding.edtOtherLoginCertify)
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
//        dismissLoadingDialog()
        if (response.isSuccess) {
//            showCustomToast("로그인 성공")

            val editor: SharedPreferences.Editor = sSharedPreferences.edit()
            editor.putString(X_ACCESS_TOKEN, response.result.jwt)
            editor.putInt(MY_IDX, response.result.userIdx)
            editor.putString(MY_PHONE_NUMBER, binding.edtOtherLoginPhoneNum.text.toString())
            editor.putString(MY_NAME, binding.edtOtherLoginName.text.toString())
            editor.putString(MY_BIRTH, binding.edtOtherLoginRegistNumFr.text.toString())
            editor.putString(MY_SEX, binding.edtOtherLoginRegistNumRr.text.toString())
            editor.putString(MY_PASSWORD, binding.edtOtherLoginCertify.text.toString())
            editor.commit()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } else {
            if (response.code == 2023) {
                editShopName()
            } else {
                btnClickCnt--
                showCustomToast("${response.message ?: ""}\n처음부터 다시 시도해주세요.")
            }
        }
    }

    override fun onPostLoginFailure(message: String) {
//        dismissLoadingDialog()
        btnClickCnt--
        showCustomToast("로그인 요청이 실패하였습니다.\n다시 시도해주세요.")
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
//        dismissLoadingDialog()
        if (response.isSuccess) {
            val editor: SharedPreferences.Editor = sSharedPreferences.edit()
            editor.putString(X_ACCESS_TOKEN, response.result.jwt)
            editor.putInt(MY_IDX, response.result.idx)
            editor.putString(MY_PHONE_NUMBER, binding.edtOtherLoginPhoneNum.text.toString())
            editor.putString(MY_NAME, binding.edtOtherLoginName.text.toString())
            editor.putString(MY_BIRTH, binding.edtOtherLoginRegistNumFr.text.toString())
            editor.putString(MY_SEX, binding.edtOtherLoginRegistNumRr.text.toString())
            editor.putString(MY_PASSWORD, binding.edtOtherLoginCertify.text.toString())
            editor.putString(MY_SHOP_NAME, binding.edtOtherLoginShopName.text.toString())
            editor.commit()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onPostSignUpFailure(message: String) {
//        dismissLoadingDialog()
        showCustomToast("회원가입 요청이 실패하였습니다.\n다시 시도해주세요.")
    }
}