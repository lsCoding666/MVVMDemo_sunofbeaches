package net.shuzhi.mvvmdemo.domain

import net.shuzhi.mvvmdemo.api.ApiException

/**
 * @author 梁爽
 * @create 2020/11/6 20:00
 */
data class ResultData<T>(
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: T
) {
    companion object{
        const val CODE_SUCCESS = 10000
    }

    fun  apiData():T{
        //如果是成功的code，我们就返回数据，否则抛出异常
        if (code==CODE_SUCCESS){
            return data
        }else{
            throw ApiException(code,message)
        }
    }
}