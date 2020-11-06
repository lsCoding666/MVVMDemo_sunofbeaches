package net.shuzhi.mvvmdemo.player

import androidx.lifecycle.LiveData

/**
 * @author 梁爽
 * @create 2020/11/6 13:47
 */
class LivePlayerState private constructor() : LiveData<PlayerPresenter.PlayState>() {
    companion object{
        val instance by lazy { LivePlayerState() }
    }

    public override fun postValue(value: PlayerPresenter.PlayState?) {
        super.postValue(value)
    }
}