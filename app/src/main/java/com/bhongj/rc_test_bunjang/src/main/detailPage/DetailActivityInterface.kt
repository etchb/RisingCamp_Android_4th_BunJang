package com.bhongj.rc_test_bunjang.src.main.detailPage

import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DeleteResponse
import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DetailResponse

interface DetailActivityInterface {

    fun onGetDataSuccess(response: DetailResponse)

    fun onGetDataFailure(message: String)

    fun onPatchSuccess(response: DeleteResponse)

    fun onPatchFailure(message: String)
}