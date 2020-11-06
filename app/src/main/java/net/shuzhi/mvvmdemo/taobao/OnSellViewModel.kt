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
    val contentList = MutableLiveData<MutableList<MapData>>()

    val loadState = MutableLiveData<LoadState>()

    private var isLoaderMode = false

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
    fun loaderMode() {
        isLoaderMode = true
        loadState.value = LoadState.LOADER_MORE_LOADING
        println("load more...")
        //去加载更多内容
        mCurrentPage++
        this.listContentByPage(mCurrentPage)
    }

    /**
     * 加载首页内容
     */
    fun loadContent() {
        isLoaderMode = false
        loadState.value = LoadState.LOADING
        this.listContentByPage(mCurrentPage)
    }

    private fun listContentByPage(page: Int) {
        viewModelScope.launch {
            try {
                val onSellList = onSellRepository.getOnSellList(page)
                val oldValue = contentList.value ?: mutableListOf()
                oldValue.addAll(
                    onSellList.tbk_dg_optimus_material_response.result_list.map_data
                )
                contentList.value = oldValue
                println("result size = ${onSellList.tbk_dg_optimus_material_response.result_list.map_data.size}")
                if (onSellList.tbk_dg_optimus_material_response.result_list.map_data.isEmpty()) {
                    loadState.value =
                        if (isLoaderMode) LoadState.LOADER_MORE_EMPTY else LoadState.EMPTY
                } else {
                    loadState.value = LoadState.SUCCESS
                    println("load success")
                }

            } catch (e: Exception) {
                mCurrentPage--
                if (e is NullPointerException) {
                    //TODO:没有更多的时候,会有一个空指针回来
                    loadState.value = LoadState.LOADER_MORE_EMPTY
                }
                e.printStackTrace()
                loadState.value = if (isLoaderMode) LoadState.LOADER_MORE_ERROR else LoadState.ERROR
            }

        }
    }


}