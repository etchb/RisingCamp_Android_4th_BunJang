package com.bhongj.rc_test_bunjang.src.main.home.brand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentBrandBinding
import com.bhongj.rc_test_bunjang.src.main.home.brand.models.Product

class BrandFragment :
    BaseFragment<FragmentBrandBinding>(FragmentBrandBinding::bind, R.layout.fragment_brand) {
    val productList = mutableListOf<Product>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BrandRcyAdapter(productList)
        val nvidiaRcyView = binding.brandRcyNvidia
        nvidiaRcyView.adapter = adapter

        val samsungRcyView = binding.brandRcySamsung
        samsungRcyView.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productList.add(
            Product(
                img = R.drawable.img_home_ad1,
                bungaePayEnabled = true,
                title = "갤럭시 GTX970",
                price = "150,000원",
            )
        )
        productList.add(
            Product(
                img = R.drawable.img_home_ad2,
                bungaePayEnabled = true,
                title = "이거팝니다이거팝니다이거팝니다.2",
                price = "80,000원",
            )
        )
        productList.add(
            Product(
                img = R.drawable.img_home_ad3,
                bungaePayEnabled = false,
                title = "이거팝니다이거팝니다이거팝니다.3",
                price = "30,000원",
            )
        )
        productList.add(
            Product(
                img = R.drawable.img_home_ad4,
                bungaePayEnabled = false,
                title = "이거팝니다.4",
                price = "90,000원",
            )
        )
        productList.add(
            Product(
                img = R.drawable.img_home_ad5,
                bungaePayEnabled = true,
                title = "이거팝니다.5",
                price = "90,000원",
            )
        )
        productList.add(Product())
        productList.add(Product())
        productList.add(Product())
        productList.add(Product())
        productList.add(Product())
        productList.add(Product())
        productList.add(Product())
        productList.add(Product())

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}