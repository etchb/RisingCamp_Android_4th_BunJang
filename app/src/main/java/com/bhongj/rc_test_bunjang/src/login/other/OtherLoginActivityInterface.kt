package com.bhongj.rc_test_bunjang.src.login.other

import com.bhongj.rc_test_bunjang.src.login.other.models.LoginResponse
import com.bhongj.rc_test_bunjang.src.login.other.models.SignUpResponse

interface OtherLoginActivityInterface {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}