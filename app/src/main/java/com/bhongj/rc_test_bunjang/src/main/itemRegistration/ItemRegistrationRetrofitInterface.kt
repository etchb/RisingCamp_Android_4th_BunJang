package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.PostRegistrationRequest
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ItemRegistrationRetrofitInterface {
    @POST("/app/products")
    fun postResponse(
        @Body postRegistrationRequest: PostRegistrationRequest,
    ): Call<RegistrationResponse>
}
