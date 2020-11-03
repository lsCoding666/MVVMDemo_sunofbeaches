package net.shuzhi.mvvmdemo.model

import net.shuzhi.mvvmdemo.http.API
import java.util.Random

/**
 * @author 梁爽
 * @create 2020/10/23 18:47
 */
class UserModel {

    private val api by lazy { API() }

    private val random =Random();

    companion object{
        const val STATE_LOGIN_LOADING = 0
        const val STATE_LOGIN_SUCCESS = 1
        const val STATE_LOGIN_FAILED = 2
    }

    /**
     * 登录
     */
    fun doLogin(
        account: String,
        password: String,
        block: (Int) -> Unit
    ) {
        block.invoke(STATE_LOGIN_LOADING)
        //开始去登录api
        //api.login()
        //有结果，耗时操作 开线程
        //异步操作 切换回主线程更新ui
        //随机结果 或者是0或者是1
        val randomValue = random.nextInt(2)
        if (randomValue == 0){
            block.invoke(STATE_LOGIN_SUCCESS)
        }else{
            block.invoke(STATE_LOGIN_FAILED)
        }
    }

    fun checkUserState(account:String,block:(Int)->Unit){
        //0表示账号已经注册
        //1表示账号没有注册
        block.invoke(random.nextInt(2))
    }

}