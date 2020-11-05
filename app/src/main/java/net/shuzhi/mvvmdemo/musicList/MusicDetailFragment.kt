package net.shuzhi.mvvmdemo.musicList

import net.shuzhi.mvvmdemo.base.BaseFragment

/**
 * @author 梁爽
 * @create 2020/11/5 12:34
 */
class MusicDetailFragment : BaseFragment() {


    private val musicPresenter by lazy { MusicPresenter(this) }


}