package net.shuzhi.mvvmdemo.taobao

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author 梁爽
 * @create 2020/11/6 19:12
 */
class RecommendViewModel : ViewModel() {

    val contentList = MutableLiveData<MutableList<String>>()
}