package com.bhongj.rc_test_bunjang.src.main.myPage.models

data class MyPageResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<List<MyPageResult>>
)