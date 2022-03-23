package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentRecmndBinding
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.Product

class RecmndFragment :
    BaseFragment<FragmentRecmndBinding>(FragmentRecmndBinding::bind, R.layout.fragment_recmnd) {
    val productList = mutableListOf<Product>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductRcyAdapter(productList)
        val productRcyView = binding.rcyHomeProduct
        productRcyView.layoutManager = GridLayoutManager(context, 2)
        productRcyView.setHasFixedSize(true)
        productRcyView.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productList.add(
            Product(img = R.drawable.img_home_ad1,
            bungaePayEnabled = true,
            title = "갤럭시 GTX970",
            price = "150,000원",
            region = "지역정보 없음",
            time = 1,
            heartCnt = 0
        )
        )
        productList.add(
            Product(img = R.drawable.img_home_ad2,
            bungaePayEnabled = true,
            title = "이거팝니다이거팝니다이거팝니다.2",
            price = "80,000원",
            region = "화성시2",
            time = 123,
            heartCnt = 0
        )
        )
        productList.add(
            Product(img = R.drawable.img_home_ad3,
            bungaePayEnabled = false,
            title = "이거팝니다이거팝니다이거팝니다.3",
            price = "30,000원",
            region = "화성시3",
            time = 123,
            heartCnt = 0
        )
        )
        productList.add(
            Product(img = R.drawable.img_home_ad4,
            bungaePayEnabled = false,
            title = "이거팝니다.4",
            price = "90,000원",
            region = "화성시4",
            time = 123,
        )
        )
        productList.add(
            Product(img = R.drawable.img_home_ad5,
            bungaePayEnabled = true,
            title = "이거팝니다.5",
            price = "90,000원",
            region = "화성시5",
            time = 123,
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