package com.bhongj.rc_test_bunjang.src.main.detailPage

import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DetailResponse

interface DetailActivityInterface {

    fun onGetDataSuccess(response: DetailResponse)

    fun onGetDataFailure(message: String)
}