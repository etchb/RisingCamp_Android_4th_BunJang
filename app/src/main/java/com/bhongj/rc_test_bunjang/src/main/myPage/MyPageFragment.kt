package com.bhongj.rc_test_bunjang.src.main.myPage

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.config.ApplicationClass.Companion.MY_SHOP_NAME
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentMyPageBinding
import com.bhongj.rc_test_bunjang.src.main.myPage.models.MyPageResponse
import com.bhongj.rc_test_bunjang.src.main.myPage.setting.SetActivity

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page),
    MyPageFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataList()

        binding.tlbMySetting.setOnClickListener {
            val intent = Intent(requireContext(), SetActivity::class.java)
            startActivity(intent)
        }
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

        val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString(MY_SHOP_NAME, result.userShopName)
        editor.commit()

        binding.viewWhite.visibility = View.GONE
    }

    override fun onGetDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}