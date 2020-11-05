package net.shuzhi.mvvmdemo.player

import android.os.Looper
import net.shuzhi.mvvmdemo.App
import net.shuzhi.mvvmdemo.lifecycle.*

/**
 * @author 梁爽
 * @create 2020/11/4 22:23
 * 数据容器 可以监听数据的变化
 */
class DataListenerContainer<T> {

    private val blocks = arrayListOf<(T?) -> Unit>()
    private val viewLifecycleProvides = hashMapOf<(T?) -> Unit, LifecycleProvider>()

    var value: T? = null
        //当数据变化 的时候就通知更新
        set(value) {
            //视频中漏了这一行
            field = value
            //判断当前线程是不是主线程,否则切换到主线程运行
            if (Looper.getMainLooper().thread === Thread.currentThread()) {
                //判断对应view的生命周期是什么

                blocks.forEach {
                    dispatchValue(it, value)

                }
            } else {
                App.handler.post {
                    blocks.forEach { dispatchValue(it, value) }
                }
            }

        }

    private fun dispatchValue(it: (T?) -> Unit, value: T?) {
        val viewModelProvider: LifecycleProvider? = viewLifecycleProvides[it]
        if (viewModelProvider != null && viewModelProvider.isAtLeast(LifeState.START)) {
            println("更新ui...")
            it.invoke(value)
        }
    }

    /**
     * 有可能有多个view进行监听
     * 所以owner->block
     * 我们得管理起来
     */
    fun addListener(owner: ILifecycleOwner, valueObserver: (T?) -> Unit) {
        val lifecycleProvider = owner.getLifecycleProvider()
        viewLifecycleProvides[valueObserver] = lifecycleProvider
        //当view destroy的时候 要从集合中删除
        val observerWrapper = ValueObserverWrapper(valueObserver)
        lifecycleProvider.addLifeListener(observerWrapper)
        if (!blocks.contains(valueObserver)) {
            blocks.add(valueObserver)
        }
    }

    inner class ValueObserverWrapper(private val valueObserver: (T?) -> Unit) : AbsLifecycle() {

        override fun onViewLifeStateChange(state: LifeState) {
            //当我监听到当前的view生命周期为destroy的时候，就把LifeProvider从集合中删除
            if (state == LifeState.DESTROY) {
                viewLifecycleProvides.remove(valueObserver)
            }
        }

    }

}