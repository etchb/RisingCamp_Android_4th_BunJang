package com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify

import com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify.models.UserInfoResponse
import com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify.models.UserOutResponse

interface UserInfoActiviyInterface {

    fun onPatchSexSuccess(response: UserInfoResponse)

    fun onPatchSexFailure(message: String)

    fun onPatchPhoneNumSuccess(response: UserInfoResponse)

    fun onPatchPhoneNumFailure(message: String)

    fun onPatchBirthSuccess(response: UserInfoResponse)

    fun onPatchBirthFailure(message: String)

    fun onPatchShopSettingSuccess(response: UserInfoResponse)

    fun onPatchShopSettingFailure(message: String)

    fun onPatchUserOutSuccess(response: UserOutResponse)

    fun onPatchUserOutFailure(message: String)
}