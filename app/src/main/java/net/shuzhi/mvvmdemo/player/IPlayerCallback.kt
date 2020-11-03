package net.shuzhi.mvvmdemo.player

/**
 * @author 梁爽
 * @create 2020/10/31 16:55
 */
interface IPlayerCallback  {
    fun onTitleChange(title:String)

    fun onProgressChange(current:Int)

    fun onPlaying()

    fun onPlayerPause()

    fun onCoverChange(cover:String)
}