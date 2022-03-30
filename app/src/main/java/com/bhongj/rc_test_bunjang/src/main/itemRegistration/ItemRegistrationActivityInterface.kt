package com.bhongj.rc_test_bunjang.src.main.itemRegistration

import com.bhongj.rc_test_bunjang.src.main.itemRegistration.models.RegistrationResponse

interface ItemRegistrationActivityInterface {

    fun onPostDataSuccess(response: RegistrationResponse)

    fun onPostDataFailure(message: String)
}