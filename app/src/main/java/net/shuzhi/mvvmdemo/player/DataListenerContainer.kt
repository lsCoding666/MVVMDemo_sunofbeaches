package net.shuzhi.mvvmdemo.player

import android.os.Looper
import net.shuzhi.mvvmdemo.App

/**
 * @author 梁爽
 * @create 2020/11/4 22:23
 * 数据容器 可以监听数据的变化
 */
class DataListenerContainer<T> {

    private val blocks = arrayListOf<(T?) -> Unit>()

    var value: T? = null
        //当数据变化 的时候就通知更新
        set(value) {
            //视频中漏了这一行
            field = value
            //判断当前线程是不是主线程,否则切换到主线程运行
            if (Looper.getMainLooper().thread === Thread.currentThread()){
                blocks.forEach { it.invoke(value) }
            }else{
                App.handler.post{
                    blocks.forEach { it.invoke(value) }
                }
            }

        }

    fun addListener(block: (T?) -> Unit) {
        if (!blocks.contains(block)) {
            blocks.add(block)
        }
    }


}