package com.bhongj.rc_test_bunjang.src.main.myPage

import android.os.Bundle
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_SHOP_NAME
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivitySetBinding

class SetActivity : BaseActivity<ActivitySetBinding>(ActivitySetBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setBtnLogout.text = "로그아웃 (${intent.getStringExtra(MY_SHOP_NAME)})"

        binding.setBtnLogout.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            finish()
        }
    }
}