package net.shuzhi.mvvmdemo.lifecycle

/**
 * @author 梁爽
 * @create 2020/11/5 13:10
 */
interface ILifecycleOwner {

    fun  getLifecycleProvider():LifecycleProvider
}