package com.bhongj.rc_test_bunjang.src.main.myPage

import com.bhongj.rc_test_bunjang.src.main.myPage.models.MyPageResponse

interface MyPageFragmentInterface {

    fun onGetDataSuccess(response: MyPageResponse)

    fun onGetDataFailure(message: String)
}