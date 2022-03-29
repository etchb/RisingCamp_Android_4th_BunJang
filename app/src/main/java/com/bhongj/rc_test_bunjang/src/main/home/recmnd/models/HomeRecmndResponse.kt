package com.bhongj.rc_test_bunjang.src.main.home.recmnd.models

data class HomeRecmndResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<List<HomeRecmndItem>>
)