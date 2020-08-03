package com.xzy.textinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * TextInputLayout 的用法
 * https://www.jianshu.com/p/9ccb0e3c0010
 * https://blog.csdn.net/qq_20785431/article/details/52270466
 * 阿里图标库 https://www.iconfont.cn/search/index
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_submit.setOnClickListener {
            val userNameText = et_name.text.toString().trim()
            val userPwdText = et_password.text.toString().trim()
            if (userNameText.isEmpty()) {
                tl_username.isErrorEnabled = true
                tl_username.error = "用户名不能为空"
            } else {
                tl_username.isErrorEnabled = false
            }

            if (userPwdText.isEmpty()) {
                tl_password.isErrorEnabled = true
                tl_password.error = "密码不能为空"
            } else {
                tl_password.isErrorEnabled = false
            }
        }
    }
}
