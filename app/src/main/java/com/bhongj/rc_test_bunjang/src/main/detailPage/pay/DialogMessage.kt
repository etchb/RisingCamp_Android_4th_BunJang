package com.bhongj.rc_test_bunjang.src.main.detailPage.pay

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.bhongj.rc_test_bunjang.R

class DialogMessage(context: Context) {
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감
    private lateinit var lblDesc: TextView
    private lateinit var btnOK: Button
    private lateinit var listener: MyDialogOKClickedListener

    fun start(content: String) {

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(R.layout.dialog_message)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        lblDesc = dlg.findViewById(R.id.dia_message)
        lblDesc.text = content

        btnOK = dlg.findViewById(R.id.dia_btn_accept)
        btnOK.setOnClickListener {
            listener.onOKClicked("확인을 눌렀습니다")
            dlg.dismiss()
        }

        dlg.show()
    }

    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.listener = object : MyDialogOKClickedListener {
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }

    interface MyDialogOKClickedListener {
        fun onOKClicked(content: String)
    }
}
