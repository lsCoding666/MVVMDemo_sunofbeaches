package net.shuzhi.mvvmdemo.musicList

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import net.shuzhi.mvvmdemo.lifecycle.AbsLifecycle
import net.shuzhi.mvvmdemo.lifecycle.ILifecycle
import net.shuzhi.mvvmdemo.lifecycle.LifeState
import net.shuzhi.mvvmdemo.player.DataListenerContainer
import net.shuzhi.mvvmdemo.player.domain.Music

/**
 * @author 梁爽
 * @create 2020/11/4 23:20
 */
class MusicPresenter(owner: LifecycleOwner) {

    private val viewLifeImpl by lazy { ViewLifeImpl() }

    init {
        owner.lifecycle.addObserver(viewLifeImpl)
    }

    private val musicModel by lazy {
        MusicModel()
    }

    val liveMusicList = MutableLiveData<List<Music>>()

    enum class MusicLoadState {
        LOADING, EMPTY, SUCCESS, ERROR
    }

    val musicList = DataListenerContainer<List<Music>>()
    val loadState = DataListenerContainer<MusicLoadState>()

    private val page = 1
    private val size = 30
    fun getMusic() {
        loadState.value = MusicLoadState.LOADING
        //从model层获取音乐列表
        musicModel.loadMusicByPage(page, size, object : MusicModel.OnMusicLoadResult {
            override fun onSuccess(result: List<Music>) {
                liveMusicList.postValue(result)
                musicList.value = result
                loadState.value = if (result.isEmpty()) {
                    MusicLoadState.EMPTY
                } else {
                    MusicLoadState.SUCCESS
                }
            }

            override fun onError(msg: String, code: Int) {
                loadState.value = MusicLoadState.ERROR
                println("error...$msg,$code")
            }

        })
    }

    inner class ViewLifeImpl : LifecycleEventObserver {
        /**
         * 被动通知View层的生命周期变化
         */
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_START -> {
                    println("开始监听GPS")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    println("停止监听GPS")
                }
                else -> {

                }
            }
        }
    }
}