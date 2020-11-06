package net.shuzhi.mvvmdemo.taobao

import net.shuzhi.mvvmdemo.api.RetrofitClient

/**
 * @author 梁爽
 * @create 2020/11/6 19:47
 */
class OnSellRepository {

    suspend fun getOnSellList(page:Int) =
        RetrofitClient.apiService.getOnSellList(page).apiData()


}