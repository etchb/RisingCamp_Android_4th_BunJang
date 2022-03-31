package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.RegistrationResponse
import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.UpdateDataResponse

interface ItemRegistrationActivityInterface {

    fun onPostDataSuccess(response: RegistrationResponse)

    fun onPostDataFailure(message: String)

    fun onPatchUpdateSuccess(response: UpdateDataResponse)

    fun onPatchUpdateFailure(message: String)
}