package com.bhongj.rc_test_bunjang.src.main.search

import android.os.Bundle
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseActivity
import com.bhongj.rc_test_bunjang.databinding.ActivitySearchResultBinding

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(ActivitySearchResultBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.transition_none, R.anim.horizon_exit_right)
    }
}