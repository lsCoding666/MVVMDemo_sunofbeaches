package net.shuzhi.mvvmdemo.taobao

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.shuzhi.mvvmdemo.base.LoadState
import net.shuzhi.mvvmdemo.domain.MapData
import net.shuzhi.mvvmdemo.domain.TbkDgOptimusMaterialResponse
import java.lang.Exception
import java.lang.NullPointerException

/**
 * @author 梁爽
 * @create 2020/11/6 19:12
 */
class OnSellViewModel : ViewModel() {
    val contentList = MutableLiveData<List<MapData>>()

    val loadState = MutableLiveData<LoadState>()

    companion object {
        const val DEFAULT_PAGE = 1
    }

    //当前页
    private var mCurrentPage = DEFAULT_PAGE

    private val onSellRepository by lazy {
        OnSellRepository()
    }

    /**
     * 加载更多内容
     */
    fun loadMode() {
        println("load more...")
    }

    /**
     * 加载首页内容
     */
    fun loadContent() {
        this.listContentByPage(mCurrentPage)
    }

    private fun listContentByPage(page: Int) {
        loadState.value = LoadState.LOADING
        viewModelScope.launch {
            try{
                val onSellList = onSellRepository.getOnSellList(page)
                println("result size = ${onSellList.tbk_dg_optimus_material_response.result_list.map_data.size}")
                contentList.value = onSellList.tbk_dg_optimus_material_response.result_list.map_data
                if (onSellList.tbk_dg_optimus_material_response.result_list.map_data.isEmpty()) {
                    loadState.value = LoadState.EMPTY
                }else{
                    loadState.value = LoadState.SUCCESS
                    println("load success")
                }

            }catch (e:Exception){
                if (e is NullPointerException) {
                    //TODO:没有更多的时候,会有一个空指针回来
                }
                e.printStackTrace()
                loadState.value = LoadState.ERROR
            }

        }
    }


}