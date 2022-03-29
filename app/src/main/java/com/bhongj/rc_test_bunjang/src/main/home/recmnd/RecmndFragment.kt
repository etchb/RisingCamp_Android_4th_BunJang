package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentRecmndBinding
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndItem
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndResponse

class RecmndFragment :
    BaseFragment<FragmentRecmndBinding>(FragmentRecmndBinding::bind, R.layout.fragment_recmnd),
    RecmndFragmentInterface {
    val productList = mutableListOf<HomeRecmndItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRecmndDataList()

        val adapter = ProductRcyAdapter(requireActivity(), productList)
        val productRcyView = binding.rcyHomeProduct
        productRcyView.layoutManager = GridLayoutManager(context, 2)
        productRcyView.setHasFixedSize(true)
        productRcyView.adapter = adapter

        binding.rcyHomeProduct.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!v.canScrollVertically(1)) {
                getRecmndDataList()
            }
        }
    }

    fun getRecmndDataList() {
        showLoadingDialog(requireContext())
        RecmndService(this).tryGetRestaurantData()
    }

    override fun onGetDataSuccess(response: HomeRecmndResponse) {
        dismissLoadingDialog()
        val result = response.result[1]
        productList.addAll(result)
        binding.rcyHomeProduct.adapter?.notifyDataSetChanged()
    }

    override fun onGetDataFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}