package net.shuzhi.mvvmdemo.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import net.shuzhi.mvvmdemo.lifecycle.ILifecycleOwner
import net.shuzhi.mvvmdemo.lifecycle.LifeState
import net.shuzhi.mvvmdemo.lifecycle.LifecycleProvider

/**
 * @author 梁爽
 * @create 2020/11/5 13:00
 */
open class BaseFragment : Fragment(),ILifecycleOwner {
    val lifeProvider by lazy { LifecycleProvider() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeProvider.makeLifeState(LifeState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifeProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
        lifeProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifeProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifeProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeProvider.makeLifeState(LifeState.DESTROY)
    }

    override fun getLifecycleProvider(): LifecycleProvider {
        return lifeProvider
    }
}