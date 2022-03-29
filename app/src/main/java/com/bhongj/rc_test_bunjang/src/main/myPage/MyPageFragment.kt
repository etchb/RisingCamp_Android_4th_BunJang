package com.bhongj.rc_test_bunjang.src.main.myPage

import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentMyPageBinding
import com.bhongj.rc_test_bunjang.src.main.myPage.models.MyPageResponse

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page),
    MyPageFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataList()
    }

    fun getDataList() {
        showLoadingDialog(requireContext())
        MyPageService(this).tryGetData(
            ApplicationClass.sSharedPreferences.getInt(
                ApplicationClass.MY_IDX,
                0
            )
        )
    }

    override fun onGetDataSuccess(response: MyPageResponse) {
        dismissLoadingDialog()
        val result = response.result[0][0]
        binding.myPageMyShopName.text = result.userShopName
        binding.viewWhite.visibility = View.GONE
    }

    override fun onGetDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}