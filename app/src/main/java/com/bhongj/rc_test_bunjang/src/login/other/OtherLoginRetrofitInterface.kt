package com.bhongj.rc_test_bunjang.src.login.other

import com.bhongj.rc_test_bunjang.src.login.other.models.LoginResponse
import com.bhongj.rc_test_bunjang.src.login.other.models.PostLoginRequest
import com.bhongj.rc_test_bunjang.src.login.other.models.PostSignUpRequest
import com.bhongj.rc_test_bunjang.src.login.other.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OtherLoginRetrofitInterface {
    @POST("app/users/logIn")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>

    @POST("app/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>
}
