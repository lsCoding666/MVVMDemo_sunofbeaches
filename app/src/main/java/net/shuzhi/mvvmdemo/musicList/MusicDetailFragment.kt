package net.shuzhi.mvvmdemo.musicList

import android.os.Bundle
import androidx.fragment.app.Fragment
import net.shuzhi.mvvmdemo.base.BaseFragment
import net.shuzhi.mvvmdemo.lifecycle.LifeState
import net.shuzhi.mvvmdemo.lifecycle.LifecycleProvider

/**
 * @author 梁爽
 * @create 2020/11/5 12:34
 */
class MusicDetailFragment : BaseFragment() {


    private val musicPresenter by lazy { MusicPresenter() }

    init {
        lifecycleProvider.addLifeListener()
    }


}