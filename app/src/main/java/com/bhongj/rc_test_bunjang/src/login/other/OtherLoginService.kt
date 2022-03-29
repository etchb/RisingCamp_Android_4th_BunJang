package com.bhongj.rc_test_bunjang.src.login.other

import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.src.login.other.models.LoginResponse
import com.bhongj.rc_test_bunjang.src.login.other.models.SignUpResponse
import com.bhongj.rc_test_bunjang.src.login.other.models.PostLoginRequest
import com.bhongj.rc_test_bunjang.src.login.other.models.PostSignUpRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OtherLoginService(val otherLoginActivityInterface: OtherLoginActivityInterface) {

    fun tryPostLogin(postLoginRequest: PostLoginRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(OtherLoginRetrofitInterface::class.java)
        retrofitInterface.postLogin(postLoginRequest)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    otherLoginActivityInterface.onPostLoginSuccess(response.body() as LoginResponse)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    otherLoginActivityInterface.onPostLoginFailure(t.message ?: "통신 오류")
                }
            })
    }

    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest) {
        val otherLoginRetrofitInterface =
            ApplicationClass.sRetrofit.create(OtherLoginRetrofitInterface::class.java)
        otherLoginRetrofitInterface.postSignUp(postSignUpRequest)
            .enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    otherLoginActivityInterface.onPostSignUpSuccess(response.body() as SignUpResponse)
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    otherLoginActivityInterface.onPostSignUpFailure(t.message ?: "통신 오류")
                }
            })
    }

}
