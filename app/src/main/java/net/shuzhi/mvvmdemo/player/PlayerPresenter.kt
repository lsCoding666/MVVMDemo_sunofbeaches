package net.shuzhi.mvvmdemo.player

import net.shuzhi.mvvmdemo.player.domain.Music

/**
 * @author 梁爽
 * @create 2020/10/31 16:52
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 * --------------
 * 播放的状态
 * -通知ui改变状态
 * 上一首 下一首
 * -标题变化
 * -歌曲封面变化
 *
 * 暂停
 * -更新ui为暂停
 *
 *
 * 相关数据：
 * 当前播放的歌曲
 * 播放状态
 */
class PlayerPresenter private constructor() {

    private val playerModel by lazy { PlayerModel() }

    private val player by lazy { MusicPlayer() }

    var currentMusic = DataListenerContainer<Music>()

    var currentPlayState = DataListenerContainer<PlayState>()

    companion object {
        val instance by lazy {
            PlayerPresenter()
        }
    }

    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING
    }

    /**
     * 根据状态控制播放
     */
    fun doPlayOrPause() {
        if (currentMusic.value == null) {
            //获取一首歌曲
            currentMusic.value = playerModel.getMusicById("卡农")
        }
        //开始播放音乐
        player.play(currentMusic.value)
        if (currentPlayState.value != PlayState.PLAYING) {
            //播放音乐
            currentPlayState.value = PlayState.PLAYING
        } else {
            //暂停
            currentPlayState.value = PlayState.PAUSE
        }
    }

    /**
     * 播放下一首歌曲
     */
    fun playNext() {
        currentMusic.value = playerModel.getMusicById("下一首：梦中的婚礼")
        //1. 拿到下一首歌曲 -> 变更ui 标题和封面
        //2. 设置给播放器

        //3. 等待播放的回调通知
        currentPlayState.value = PlayState.PLAYING

    }

    /**
     * 播放上一首歌曲
     */
    fun playPre() {
        currentMusic.value = playerModel.getMusicById("上一首：圣母颂")
        //1. 拿到下一首歌曲 -> 变更ui 标题和封面
        //2. 设置给播放器

        //3. 等待播放的回调通知
        currentPlayState.value = PlayState.PLAYING
    }

}