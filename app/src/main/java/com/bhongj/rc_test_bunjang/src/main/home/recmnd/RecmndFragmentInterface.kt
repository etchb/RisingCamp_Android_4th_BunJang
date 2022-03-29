package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndResponse

interface RecmndFragmentInterface {

    fun onGetDataSuccess(response: HomeRecmndResponse)

    fun onGetDataFailure(message: String)
}