package com.bhongj.rc_test_bunjang.src.main.chat

import android.os.Bundle
import android.view.View
import com.bhongj.rc_test_bunjang.R
import com.bhongj.rc_test_bunjang.config.BaseFragment
import com.bhongj.rc_test_bunjang.databinding.FragmentChatBinding

class ChatFragment :
    BaseFragment<FragmentChatBinding>(FragmentChatBinding::bind, R.layout.fragment_chat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}