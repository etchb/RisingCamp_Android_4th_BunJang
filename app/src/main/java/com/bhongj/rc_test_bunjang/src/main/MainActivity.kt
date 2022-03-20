package com.bhongj.rc_test_bunjang.src.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivityMainBinding
import com.bhongj.rc_test_bunjang.src.main.chat.ChatFragment
import com.bhongj.rc_test_bunjang.src.main.home.HomeFragment
import com.bhongj.rc_test_bunjang.src.main.myPage.MyPageFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
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
}