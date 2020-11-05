package net.shuzhi.mvvmdemo.musicList

import net.shuzhi.mvvmdemo.player.domain.Music

/**
 * @author 梁爽
 * @create 2020/11/4 23:21
 */
class MusicModel {

    fun loadMusicByPage(page: Int, size: Int, callback: OnMusicLoadResult) {
        val result = arrayListOf<Music>()
        Thread {
            for (i in (0 until size)) {
                Thread.sleep(100)
                result.add(
                    Music(
                        "音乐的名称 $i",
                        "cover封面 $i",
                        "url == > $i"
                    )
                )
            }
            //数据请求完成
            //通知ui更新
            callback.onSuccess(result)
        }.start()
    }

    interface OnMusicLoadResult {
        fun onSuccess(result: List<Music>)
        fun onError(msg: String, code: Int)
    }
}