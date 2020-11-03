package net.shuzhi.mvvmdemo.player

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
 * -
 */
class PlayerPresenter {

    enum class PlayState {
        NONE, PLAYING, PAUSE, LOADING
    }

    private val callbackList = arrayListOf<IPlayerCallback>()

    private var currentPlayState = PlayState.NONE;

    fun registerCallback(callback: IPlayerCallback) {
        if (!callbackList.contains(callback)) {
            callbackList.add(callback)
        }
    }

    fun unRegisterCallback(callback: IPlayerCallback) {
        //这里比视频里的多了一个判断
        if (!callbackList.contains(callback)) {
            callbackList.remove(callback)
        }
    }

    /**
     * 根据状态控制播放
     */
    fun doPlayOrPause() {
        dispatchTitleChange("当前播放的歌曲标题...")
        dispatchCoverChange("当前播放的歌曲封面...")
        if (currentPlayState != PlayState.PLAYING) {
            //播放音乐
            dispatchPlayingState()
        } else {
            //暂停
            dispatchPauseState()
        }
    }

    private fun dispatchPauseState() {
        callbackList.forEach {
            it.onPlayerPause()
        }
    }

    private fun dispatchPlayingState() {
        callbackList.forEach {
            it.onPlaying()
        }
    }

    /**
     * 播放下一首歌曲
     */
    fun playNext() {
        //1. 拿到下一首歌曲 -> 变更ui 标题和封面
        dispatchTitleChange("切换到下一首，标题变化了...")
        dispatchCoverChange("切换到下一首，封面变化了...")
        //2. 设置给播放器

        //3. 等待播放的回调通知


    }

    private fun dispatchCoverChange(cover: String) {
        callbackList.forEach {
            it.onCoverChange(cover)
        }
    }

    private fun dispatchTitleChange(title: String) {
        callbackList.forEach {
            it.onTitleChange(title)
        }
    }

    /**
     * 播放上一首歌曲
     */
    fun playPre() {
        //1. 拿到下一首歌曲 -> 变更ui 标题和封面
        dispatchTitleChange("切换到上一首，标题变化了...")
        dispatchCoverChange("切换到上一首，封面变化了...")
        //2. 设置给播放器

        //3. 等待播放的回调通知

    }

}