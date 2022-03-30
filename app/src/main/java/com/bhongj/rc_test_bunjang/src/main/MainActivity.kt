package com.bhongj.rc_test_bunjang.src.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityMainBinding
import com.bhongj.rc_test_bunjang.src.main.chat.ChatFragment
import com.bhongj.rc_test_bunjang.src.main.home.HomeFragment
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.ItemRegistrationActivity
import com.bhongj.rc_test_bunjang.src.main.myPage.MyPageFragment
import com.bhongj.rc_test_bunjang.src.main.search.SearchActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()

        if (intent.getBooleanExtra("isPaymentOut", false)) {
            showCustomToast("정상적으로 구매가 완료되었습니다. 상품인덱스 : ${intent.getIntExtra("itemIdx", 0)}")
        }
        if (intent.getBooleanExtra("isRegistration", false)) {
            showCustomToast("정상적으로 등록이 완료되었습니다. 상품이름 : ${intent.getStringExtra("itemName")}")
        }
    }

    private fun initBottomNavigation() {
        val fragmentList = arrayListOf<Fragment>()

        fragmentList.addAll(arrayListOf<Fragment>(HomeFragment(), ChatFragment(), MyPageFragment()))

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, fragmentList[0])
            .commitAllowingStateLoss()

        binding.mainBtmNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main_btm_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, fragmentList[0])
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_btm_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    return@setOnItemSelectedListener false
                }
                R.id.menu_main_btm_add -> {
                    startActivity(Intent(this, ItemRegistrationActivity::class.java))
                    return@setOnItemSelectedListener false
                }
                R.id.menu_main_btm_chat -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, fragmentList[1])
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_btm_my -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, fragmentList[2])
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 1000) {
            backKeyPressedTime = System.currentTimeMillis()
            showCustomToast("버튼을 한번 더 누르시면 종료됩니다.")
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 1000) {
            finish()
        }
        super.onBackPressed()
    }
}