package com.bhongj.rc_test_bunjang.src.main.itemRegistration.models

data class RegistrationResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<List<Any>>
)