package com.bhongj.rc_test_bunjang.src.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_BIRTH
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_IDX
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_NAME
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_PASSWORD
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_PHONE_NUMBER
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.sSharedPreferences
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivitySplashBinding
import com.bhongj.rc_test_bunjang.src.login.LoginActivity
import com.bhongj.rc_test_bunjang.src.login.other.OtherLoginActivityInterface
import com.bhongj.rc_test_bunjang.src.login.other.OtherLoginService
import com.bhongj.rc_test_bunjang.src.login.other.models.LoginResponse
import com.bhongj.rc_test_bunjang.src.login.other.models.PostLoginRequest
import com.bhongj.rc_test_bunjang.src.login.other.models.SignUpResponse
import com.bhongj.rc_test_bunjang.src.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate),
    OtherLoginActivityInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* init sharedPreferences */
//        val editor: SharedPreferences.Editor = sSharedPreferences.edit()
//        editor.putString(ApplicationClass.X_ACCESS_TOKEN, "None")
//        editor.putInt(MY_IDX, 0)
//        editor.putString(MY_PHONE_NUMBER, "None")
//        editor.putString(MY_NAME, "None")
//        editor.putString(MY_BIRTH, "None")
//        editor.putString(MY_PASSWORD, "None")
//        editor.commit()
        /* init sharedPreferences */

        val window = window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        ObjectAnimator.ofFloat(binding.imgSplash, View.SCALE_X, 1.3f).apply {
            duration = 300L
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(binding.imgSplash, View.SCALE_Y, 1.3f).apply {
            duration = 300L
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            OtherLoginService(this).tryPostLogin(
                PostLoginRequest(
                    phoneNumber = sSharedPreferences.getString(MY_PHONE_NUMBER, "None") ?: "None",
                    userName = sSharedPreferences.getString(MY_NAME, "None") ?: "None",
                    userBirth = sSharedPreferences.getString(MY_BIRTH, "None") ?: "None",
                    userPwd = sSharedPreferences.getString(MY_PASSWORD, "None") ?: "None"
                )
            )
        }, 1000)
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
//        dismissLoadingDialog()
        if (response.isSuccess) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onPostLoginFailure(message: String) {
//        dismissLoadingDialog()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        return
    }

    override fun onPostSignUpFailure(message: String) {
        return
    }
}