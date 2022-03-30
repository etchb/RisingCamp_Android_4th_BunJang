package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayRequest
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayResponse
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.PostRegistrationRequest
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemRegistrationService(val itemRegistrationActivityInterface: ItemRegistrationActivityInterface) {

    fun tryPostData(postRegistrationRequest: PostRegistrationRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(ItemRegistrationRetrofitInterface::class.java)
        retrofitInterface.postResponse(postRegistrationRequest)
            .enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {
                    itemRegistrationActivityInterface.onPostDataSuccess(response.body() as RegistrationResponse)
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    itemRegistrationActivityInterface.onPostDataFailure(t.message ?: "통신 오류")
                }
            })
    }

    fun tryPatchUpdateData(productIdx: Int, postRegistrationRequest: PostRegistrationRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(ItemRegistrationRetrofitInterface::class.java)
        retrofitInterface.patchUpdateResponse(productIdx, postRegistrationRequest)
            .enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {
                    itemRegistrationActivityInterface.onPostDataSuccess(response.body() as RegistrationResponse)
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    itemRegistrationActivityInterface.onPostDataFailure(t.message ?: "통신 오류")
                }
            })
    }
}
