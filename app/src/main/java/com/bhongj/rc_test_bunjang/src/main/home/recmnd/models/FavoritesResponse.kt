package com.bhongj.rc_test_bunjang.src.main.home.recmnd.models

data class FavoritesResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: FavoritesResult
)