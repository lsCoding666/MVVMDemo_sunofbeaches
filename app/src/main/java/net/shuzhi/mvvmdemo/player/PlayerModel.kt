package net.shuzhi.mvvmdemo.player

import net.shuzhi.mvvmdemo.player.domain.Music

/**
 * @author 梁爽
 * @create 2020/10/31 17:00
 */
class PlayerModel {
    fun getMusicById(id: String):Music {
         return  Music(
             "歌曲名:$id",
             "https://www.baidu.com/img/PCfb_5bf082d29588c07f842ccde3f97243ea.png",
             "https://baidu.com"
         )
    }

}