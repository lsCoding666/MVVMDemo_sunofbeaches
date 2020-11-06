package net.shuzhi.mvvmdemo.api

import net.shuzhi.mvvmdemo.domain.OnSellData
import net.shuzhi.mvvmdemo.domain.ResultData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author 梁爽
 * @create 2020/11/6 19:54
 */
interface ApiService {
    companion object{
        //经过自己的测试 这里一定要"/"结尾
        const val BASE_URL = "https://api.sunofbeach.net/shop/"
    }

    @GET("onSell/{page}")
    suspend fun getOnSellList(@Path("page")page:Int): ResultData<OnSellData>
}