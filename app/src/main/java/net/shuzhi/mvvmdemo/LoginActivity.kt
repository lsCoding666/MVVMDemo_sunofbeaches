package net.shuzhi.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import net.shuzhi.mvvmdemo.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(),
    LoginPresenter.OnCheckUserNameStateResultCallback, LoginPresenter.OnDoLoginStateChange {

    private val loginPresenter by lazy { LoginPresenter() }
    private var isUserNameAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        loginBtn.setOnClickListener {
            //登录
            toLogin()
        }
        //监听内容变化
        etUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //检查是否注册
                loginPresenter.checkUserNameState(s.toString(), this@LoginActivity)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    /**
     * 处理登录逻辑
     */
    private fun toLogin() {
        //检查登录的逻辑
        val account = etUserName.text.toString();
        val password = etPassword.text.toString();
        //密码加密
        if (!isUserNameAvailable){
            //提示用户名重复
            return
        }
        loginPresenter.doLogin(account,password,this)
    }

    override fun onAccountFormatError() {
        tvStatus.text = "账号格式错误"
    }

    override fun onPasswordEmpty() {
        tvStatus.text = "密码不能为空"
    }


    override fun onLoading() {
        tvStatus.text = "登录中..."
    }

    override fun onLoginSuccess() {
        tvStatus.text = "登录成功..."
        loginBtn?.isEnabled = true
    }

    override fun onLoginFailed() {
        tvStatus.text = "登录失败..."
        loginBtn?.isEnabled = true
    }

    override fun onNotExist() {
        //用户名可以用
        tvStatus.text = "用户名可用"
        isUserNameAvailable = true
    }

    override fun onExist() {
        //用户名不可以用
        tvStatus.text = "用户名已经被注册了"
        isUserNameAvailable = false
    }
}