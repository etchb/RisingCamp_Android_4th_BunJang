package com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify

import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserInfoService(val layoutInterface: UserInfoActiviyInterface) {

    fun tryPatchSex(userIdx: Int, udSexRequest: UdSexRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(UserInfoRetrofitInterface::class.java)
        retrofitInterface.patchSexResponse(userIdx, udSexRequest)
            .enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    layoutInterface.onPatchSexSuccess(response.body() as UserInfoResponse)
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    layoutInterface.onPatchSexFailure(t.message ?: "통신 오류")
                }
            })
    }

    fun tryPatchBirth(userIdx: Int, udBirthRequest: UdBirthRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(UserInfoRetrofitInterface::class.java)
        retrofitInterface.patchBirthResponse(userIdx, udBirthRequest)
            .enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    layoutInterface.onPatchBirthSuccess(response.body() as UserInfoResponse)
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    layoutInterface.onPatchBirthFailure(t.message ?: "통신 오류")
                }
            })
    }

    fun tryPatchPhoneNum(userIdx: Int, udPhoneNumRequest: UdPhoneNumRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(UserInfoRetrofitInterface::class.java)
        retrofitInterface.patchPhoneNumResponse(userIdx, udPhoneNumRequest)
            .enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    layoutInterface.onPatchPhoneNumSuccess(response.body() as UserInfoResponse)
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    layoutInterface.onPatchPhoneNumFailure(t.message ?: "통신 오류")
                }
            })
    }

    fun tryPatchShopSetting(userIdx: Int, udShopSettingRequest: UdShopSettingRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(UserInfoRetrofitInterface::class.java)
        retrofitInterface.patchShopSettingResponse(userIdx, udShopSettingRequest)
            .enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    layoutInterface.onPatchShopSettingSuccess(response.body() as UserInfoResponse)
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    layoutInterface.onPatchShopSettingFailure(t.message ?: "통신 오류")
                }
            })
    }

    fun tryPatchUserOut(userIdx: Int, userOutRequest: UserOutRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(UserInfoRetrofitInterface::class.java)
        retrofitInterface.patchUserOutResponse(userIdx, userOutRequest)
            .enqueue(object : Callback<UserOutResponse> {
                override fun onResponse(
                    call: Call<UserOutResponse>,
                    response: Response<UserOutResponse>
                ) {
                    layoutInterface.onPatchUserOutSuccess(response.body() as UserOutResponse)
                }

                override fun onFailure(call: Call<UserOutResponse>, t: Throwable) {
                    layoutInterface.onPatchUserOutFailure(t.message ?: "통신 오류")
                }
            })
    }
}
