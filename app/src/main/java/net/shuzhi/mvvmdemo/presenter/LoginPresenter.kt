package net.shuzhi.mvvmdemo.presenter

import android.text.TextUtils
import net.shuzhi.mvvmdemo.model.UserModel
import net.shuzhi.mvvmdemo.model.UserModel.Companion.STATE_LOGIN_FAILED
import net.shuzhi.mvvmdemo.model.UserModel.Companion.STATE_LOGIN_LOADING
import net.shuzhi.mvvmdemo.model.UserModel.Companion.STATE_LOGIN_SUCCESS

/**
 * @author 梁爽
 * @create 2020/10/23 19:11
 */
class LoginPresenter {
    private val userModel by lazy { UserModel() }

    fun checkUserNameState(account:String,callback: OnCheckUserNameStateResultCallback){
        userModel.checkUserState(account){
            when (it){
                0->{
                    callback.onExist()
                }
                1->{
                    callback.onNotExist()
                }
            }
        }
    }

    interface OnCheckUserNameStateResultCallback{
        fun onNotExist()
        fun onExist()
    }

    fun doLogin(userName:String ,password:String,callback: OnDoLoginStateChange){
        //检查账号格式
        if (TextUtils.isEmpty(userName)) {
            //提示
            callback.onAccountFormatError()
            return
        }
        //检查密码格式
        if (TextUtils.isEmpty(password)) {
            //提示
            callback.onPasswordEmpty()
            return
        }

        userModel.doLogin(userName,password){
            when (it){
                STATE_LOGIN_LOADING ->{
                    callback.onLoading()
                }
                STATE_LOGIN_SUCCESS->{
                    callback.onLoginSuccess()
                }
                STATE_LOGIN_FAILED->{
                    callback.onLoginFailed()
                }
            }
        }
    }

    interface OnDoLoginStateChange {
        fun onAccountFormatError()

        fun onPasswordEmpty()

        fun onLoading()

        fun onLoginSuccess()

        fun onLoginFailed()
    }
}