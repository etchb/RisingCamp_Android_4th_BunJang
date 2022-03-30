package com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify

import com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserInfoRetrofitInterface {
    @PATCH("/app/users/{userIdx}/sex")
    fun patchSexResponse(
        @Path("userIdx") userIdx: Int,
        @Body udSexRequest: UdSexRequest,
    ): Call<UserInfoResponse>

    @PATCH("/app/users/{userIdx}/birth")
    fun patchBirthResponse(
        @Path("userIdx") userIdx: Int,
        @Body udBirthRequest: UdBirthRequest
    ): Call<UserInfoResponse>

    @PATCH("/app/users/{userIdx}/phonenumber")
    fun patchPhoneNumResponse(
        @Path("userIdx") userIdx: Int,
        @Body udPhoneNumRequest: UdPhoneNumRequest
    ): Call<UserInfoResponse>

    @PATCH("/app/users/{userIdx}/setting")
    fun patchShopSettingResponse(
        @Path("userIdx") userIdx: Int,
        @Body udShopSettingRequest: UdShopSettingRequest
    ): Call<UserInfoResponse>

    @PATCH("/app/users/{userIdx}/delete")
    fun patchUserOutResponse(
        @Path("userIdx") userIdx: Int,
        @Body userOutRequest: UserOutRequest
    ): Call<UserOutResponse>
}
