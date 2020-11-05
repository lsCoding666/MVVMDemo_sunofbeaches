package net.shuzhi.mvvmdemo.lifecycle

/**
 * @author 梁爽
 * @create 2020/11/5 12:05
 */
interface ILifecycle {
    fun onCreate()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()
}