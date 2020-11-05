package net.shuzhi.mvvmdemo.lifecycle

/**
 * @author 梁爽
 * @create 2020/11/5 12:38
 * 管理所注册进来的接口，这个接口就是ILifecycle
 * 保存当前view的生命周期状态
 */
class LifecycleProvider {
    private val lifecycleListener = arrayListOf<AbsLifecycle>()

    private var currentLifeState: LifeState = LifeState.DESTROY

    fun addLifeListener(listener: AbsLifecycle) {
        if (!lifecycleListener.contains(listener)) {
            lifecycleListener.add(listener)
        }
    }

    fun removeLifeCircleListener(listener: AbsLifecycle) {
        if (lifecycleListener.contains(listener)) {
            lifecycleListener.remove(listener)
        }
    }

    fun makeLifeState(state: LifeState) {
        currentLifeState = state
        lifecycleListener.forEach {
            it.onViewLifeStateChange(state)
        }
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

    /**
     * 由于需要ui可见的时候才能更新ui
     * 所以这个方法就确保了这件事情，必须要当前状态至少为OnStart的时候才能更新ui
     * 由于这里用的是大于而不是大于等于，这就导致了其实是onResume的时候才能更新ui
     */
    fun isAtLeast(state: LifeState): Boolean {
        println("current state $currentLifeState === $state")
        val isAsLeastState = currentLifeState > state
        println("isAsLeastState --> $isAsLeastState")
        return isAsLeastState
    }
}