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
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_SHOP_NAME
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.sSharedPreferences
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivitySetBinding
import com.bhongj.rc_test_bunjang.src.login.LoginActivity

class SetActivity : BaseActivity<ActivitySetBinding>(ActivitySetBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setBtnLogout.text = "로그아웃 (${intent.getStringExtra(MY_SHOP_NAME)})"

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
    }
}