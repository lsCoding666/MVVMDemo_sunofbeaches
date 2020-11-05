package net.shuzhi.mvvmdemo.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import net.shuzhi.mvvmdemo.lifecycle.LifeState
import net.shuzhi.mvvmdemo.lifecycle.LifecycleProvider

/**
 * @author 梁爽
 * @create 2020/11/5 13:00
 */
open class BaseFragment : Fragment() {
    val lifecycleProvider by lazy { LifecycleProvider() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleProvider.makeLifeState(LifeState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycleProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleProvider.makeLifeState(LifeState.DESTROY)
    }
}