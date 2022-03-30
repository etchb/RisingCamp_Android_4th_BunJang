package com.bhongj.rc_test_bunjang.src.main.myPage.setting

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_BIRTH
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_IDX
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_NAME
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_PASSWORD
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_PHONE_NUMBER
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_SEX
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_SHOP_NAME
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.sSharedPreferences
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivitySetBinding
import com.bhongj.rc_test_bunjang.src.login.LoginActivity
import com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify.UserInfoModifyActivity

class SetActivity : BaseActivity<ActivitySetBinding>(ActivitySetBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setBtnLogout.setOnClickListener {
            val dlg = DialogLogout(this)
            dlg.setOnClickedListener { content ->
                if (content == "확인을 눌렀습니다") {
                    /* init sharedPreferences */
                    val editor: SharedPreferences.Editor = sSharedPreferences.edit()
                    editor.putString(ApplicationClass.X_ACCESS_TOKEN, "None")
                    editor.putInt(MY_IDX, 0)
                    editor.putString(MY_PHONE_NUMBER, "None")
                    editor.putString(MY_NAME, "None")
                    editor.putString(MY_BIRTH, "None")
                    editor.putString(MY_SEX, "None")
                    editor.putString(MY_PASSWORD, "None")
                    editor.commit()
                    /* init sharedPreferences */
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            dlg.start("show Dialog")
        }

        binding.setBtnUser.setOnClickListener {
            val intent = Intent(this, UserInfoModifyActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onStart() {
        super.onStart()

        binding.setBtnLogout.text = "로그아웃 (${sSharedPreferences.getString(MY_SHOP_NAME, "none")})"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                1 -> {
                    showCustomToast("사용자정보가 업데이트되었습니다.")
                }
            }
        }
    }
}