package com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.bhongj.rc_test_bunjang.databinding.ActivityUserInfoModifyBinding
import com.bhongj.rc_test_bunjang.src.login.LoginActivity
import com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify.models.*

class UserInfoModifyActivity :
    BaseActivity<ActivityUserInfoModifyBinding>(ActivityUserInfoModifyBinding::inflate),
    UserInfoActiviyInterface {

    var successCnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.edtName.text = sSharedPreferences.getString(MY_NAME, "")
        binding.edtPhoneNum.setText(sSharedPreferences.getString(MY_PHONE_NUMBER, ""))
        binding.edtRegistNumFr.setText(sSharedPreferences.getString(MY_BIRTH, ""))
        binding.edtRegistNumRr.setText(sSharedPreferences.getString(MY_SEX, ""))
        binding.edtShopName.setText(sSharedPreferences.getString(MY_SHOP_NAME, ""))

        val myIdx = sSharedPreferences.getInt(MY_IDX, 0)

        binding.btnUserInfoUpdate.setOnClickListener {
            successCnt = 0

            if (binding.edtRegistNumRr.text.toString() == "1") {
                showLoadingDialog(this)
                UserInfoService(this).tryPatchSex(myIdx, UdSexRequest(1))
            } else {
                showLoadingDialog(this)
                UserInfoService(this).tryPatchSex(myIdx, UdSexRequest(2))
            }

            UserInfoService(this).tryPatchBirth(
                myIdx,
                UdBirthRequest(binding.edtRegistNumFr.text.toString())
            )
            UserInfoService(this).tryPatchPhoneNum(
                myIdx,
                UdPhoneNumRequest(binding.edtPhoneNum.text.toString())
            )
            UserInfoService(this).tryPatchShopSetting(
                myIdx,
                UdShopSettingRequest(shopName = binding.edtShopName.text.toString())
            )
        }

        binding.btnUserOut.setOnClickListener {
            sSharedPreferences.getString(X_ACCESS_TOKEN, "")?.let { Log.d("TEST X_ACCESS_TOKEN", it) }
            Log.d("TEST MY_IDX", sSharedPreferences.getInt(MY_IDX, 0).toString())

            val dlg = DialogUserOut(this)
            dlg.setOnClickedListener { content ->
                if (content == "확인을 눌렀습니다") {
                    Log.d("TEST content", content)
                    showLoadingDialog(this)
                    UserInfoService(this).tryPatchUserOut(
                        myIdx,
                        UserOutRequest(closingReason = binding.edtUserOutReason.text.toString())
                    )
                }
            }
            dlg.start(
                title = "회원탈퇴",
                content = "탈퇴 시 계정의 모든 정보는 삭제되며 재가입시에도 복구되지 않습니다.\n\n회원탈퇴를 진행하시겠습니까?"
            )
        }

    }

    override fun onPatchSexSuccess(response: UserInfoResponse) {
        dismissLoadingDialog()

        if (response.isSuccess) {
            val editor: SharedPreferences.Editor = sSharedPreferences.edit()
            editor.putString(MY_SEX, binding.edtRegistNumRr.text.toString())
            editor.commit()
            successCnt++
            if (successCnt == 4) {
                setResult(RESULT_OK)
                finish()
            }
        } else {
            binding.edtRegistNumRr.setText(response.message)
        }
    }

    override fun onPatchSexFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPatchPhoneNumSuccess(response: UserInfoResponse) {
        dismissLoadingDialog()

        if (response.isSuccess) {
            val editor: SharedPreferences.Editor = sSharedPreferences.edit()
            editor.putString(MY_PHONE_NUMBER, binding.edtPhoneNum.text.toString())
            editor.commit()
            successCnt++
            if (successCnt == 4) {
                setResult(RESULT_OK)
                finish()
            }
        } else {
            binding.edtPhoneNum.setText(response.message)
        }
    }

    override fun onPatchPhoneNumFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
        binding.edtPhoneNum.setText(message)
    }

    override fun onPatchBirthSuccess(response: UserInfoResponse) {
        dismissLoadingDialog()

        if (response.isSuccess) {
            val editor: SharedPreferences.Editor = sSharedPreferences.edit()
            editor.putString(MY_BIRTH, binding.edtRegistNumFr.text.toString())
            editor.commit()
            successCnt++
            if (successCnt == 4) {
                setResult(RESULT_OK)
                finish()
            }
        } else {
            binding.edtRegistNumFr.setText(response.message)
        }
    }

    override fun onPatchBirthFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPatchShopSettingSuccess(response: UserInfoResponse) {
        dismissLoadingDialog()

        if (response.isSuccess) {
            val editor: SharedPreferences.Editor = sSharedPreferences.edit()
            editor.putString(MY_SHOP_NAME, binding.edtShopName.text.toString())
            editor.commit()
            successCnt++
            if (successCnt == 4) {
                setResult(RESULT_OK)
                finish()
            }
        } else {
            binding.edtShopName.setText(response.message)
        }
    }

    override fun onPatchShopSettingFailure(message: String) {

        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPatchUserOutSuccess(response: UserOutResponse) {
        dismissLoadingDialog()

        Log.d("TEST onPatchUserOutSuccess", response.toString())

        if (response.isSuccess) {
            val editor: SharedPreferences.Editor = sSharedPreferences.edit()
            editor.putString(X_ACCESS_TOKEN, "None")
            editor.putInt(MY_IDX, 0)
            editor.putString(MY_PHONE_NUMBER, "None")
            editor.putString(MY_NAME, "None")
            editor.putString(MY_BIRTH, "None")
            editor.putString(MY_SEX, "None")
            editor.putString(MY_PASSWORD, "None")
            editor.putString(MY_SHOP_NAME, "None")
            editor.commit()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } else {
            showCustomToast("다시 시도하세요.")
        }
    }

    override fun onPatchUserOutFailure(message: String) {
        Log.d("TEST onPatchUserOutFailure", message)

        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}