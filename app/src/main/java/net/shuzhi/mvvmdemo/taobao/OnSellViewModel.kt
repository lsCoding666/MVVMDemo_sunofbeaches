package net.shuzhi.mvvmdemo.taobao

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.shuzhi.mvvmdemo.domain.MapData
import net.shuzhi.mvvmdemo.domain.TbkDgOptimusMaterialResponse

/**
 * @author 梁爽
 * @create 2020/11/6 19:12
 */
class OnSellViewModel : ViewModel() {
    val contentList = MutableLiveData<List<MapData>>()

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

    }

    /**
     * 加载首页内容
     */
    fun loadContent() {
        this.listContentByPage(mCurrentPage)
    }

    private fun listContentByPage(page: Int) {
        viewModelScope.launch {
            val onSellList = onSellRepository.getOnSellList(page)
            println("result size = ${onSellList.tbk_dg_optimus_material_response.result_list.map_data.size}")
            contentList.value = onSellList.tbk_dg_optimus_material_response.result_list.map_data
            println("load success")
        }
    }


}