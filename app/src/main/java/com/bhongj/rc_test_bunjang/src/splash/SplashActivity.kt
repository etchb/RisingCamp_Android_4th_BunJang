package com.bhongj.rc_test_bunjang.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivitySplashBinding
import com.bhongj.rc_test_bunjang.src.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}