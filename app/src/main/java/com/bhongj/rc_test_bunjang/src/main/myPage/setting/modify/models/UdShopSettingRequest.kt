package com.bhongj.rc_test_bunjang.src.main.myPage.setting.modify.models

data class UdShopSettingRequest(
    val shopName : String,
    val shopAddress : String = "www.prod.hiimpedro.site",
    val avaTimeStart : String = "오전12시",
    val avaTimeEnd : String = "오전12시",
    val shopIntro : String = "판매상점 인트로로 바꾸었습니다.",
    val shopPolicy : String = "불필요한 질문은 사절합니다.",
    val preCaution : String = "채팅건 순간 보증금 5만원 겁니다.",
)
