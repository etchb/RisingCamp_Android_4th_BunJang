package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.PostRegistrationRequest
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.RegistrationResponse
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.UpdateDataResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ItemRegistrationRetrofitInterface {
    @POST("/app/products")
    fun postResponse(
        @Body postRegistrationRequest: PostRegistrationRequest,
    ): Call<RegistrationResponse>

    @PATCH("/app/products/{productIdx}/edit")
    fun patchUpdateResponse(
        @Path("productIdx") productIdx: Int,
        @Body postRegistrationRequest: PostRegistrationRequest
    ): Call<UpdateDataResponse>
}
