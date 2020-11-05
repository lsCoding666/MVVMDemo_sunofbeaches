package net.shuzhi.mvvmdemo.musicList

import net.shuzhi.mvvmdemo.base.BaseFragment

/**
 * @author 梁爽
 * @create 2020/11/5 13:02
 */
class MusicListFragment : BaseFragment() {

    private val musicPresenter by lazy { MusicPresenter() }

    init {
        lifecycleProvider.addLifeListener(musicPresenter)
    }
}