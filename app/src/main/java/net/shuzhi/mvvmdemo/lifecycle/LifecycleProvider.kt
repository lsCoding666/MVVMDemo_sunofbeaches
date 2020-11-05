package net.shuzhi.mvvmdemo.lifecycle

/**
 * @author 梁爽
 * @create 2020/11/5 12:38
 * 管理所注册进来的接口，这个接口就是ILifecycle
 * 保存当前view的生命周期状态
 */
class LifecycleProvider {
    private val lifecycleListener = arrayListOf<ILifecycle>()

    private var currentLifeState: LifeState? = null

    fun addLifeListener(listener: ILifecycle) {
        if (!lifecycleListener.contains(listener)) {
            lifecycleListener.add(listener)
        }
    }

    fun removeLifeCircleListener(listener: ILifecycle) {
        if (lifecycleListener.contains(listener)) {
            lifecycleListener.remove(listener)
        }
    }

    fun makeLifeState(state: LifeState) {
        currentLifeState = state
        when (state) {
            LifeState.CREATE -> {
                dispatchCreateState()
            }
            LifeState.DESTROY -> {
                dispatchDestroyState()
            }
            LifeState.PAUSE -> {
                dispatchPauseState()
            }
            LifeState.RESUME -> {
                dispatchResumeState()
            }
            LifeState.START -> {
                dispatchStartState()
            }
            LifeState.STOP -> {
                dispatchStopState()
            }
        }
    }

    private fun dispatchStopState() {
        lifecycleListener.forEach {
            it.onStop()
        }
    }

    private fun dispatchStartState() {
        lifecycleListener.forEach {
            it.onStart()
        }
    }

    private fun dispatchResumeState() {
        lifecycleListener.forEach {
            it.onResume()
        }
    }

    private fun dispatchPauseState() {
        lifecycleListener.forEach {
            it.onPause()
        }
    }

    private fun dispatchDestroyState() {
        lifecycleListener.forEach {
            it.onDestroy()
        }
        lifecycleListener.clear()
    }

    private fun dispatchCreateState() {
        lifecycleListener.forEach {
            it.onCreate()
        }
    }
}