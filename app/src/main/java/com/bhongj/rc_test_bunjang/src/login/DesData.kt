package com.bhongj.rc_test_bunjang.src.login

import com.bhongj.rc_test_bunjang.R

data class DesData(
    val TITLE: Int,
    val CONTENT: Int,
    val RES: Int
)

val DesDataList = mutableListOf<DesData>(
    DesData( R.string.slide_des_title1, R.string.slide_des_content1, R.drawable.login_pic_1),
    DesData( R.string.slide_des_title2, R.string.slide_des_content2, R.drawable.login_pic_2),
    DesData( R.string.slide_des_title3, R.string.slide_des_content3, R.drawable.login_pic_3),
    DesData( R.string.slide_des_title4, R.string.slide_des_content4, R.drawable.login_pic_4),
)
