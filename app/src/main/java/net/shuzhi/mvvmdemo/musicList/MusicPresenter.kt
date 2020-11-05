package net.shuzhi.mvvmdemo.musicList

import net.shuzhi.mvvmdemo.lifecycle.ILifeCircle
import net.shuzhi.mvvmdemo.player.DataListenerContainer
import net.shuzhi.mvvmdemo.player.domain.Music

/**
 * @author 梁爽
 * @create 2020/11/4 23:20
 */
class MusicPresenter : ILifeCircle{

    private val musicModel by lazy {
        MusicModel()
    }

    enum class  MusicLoadState{
        LOADING,EMPTY,SUCCESS,ERROR
    }

    val musicList = DataListenerContainer<List<Music>>()
    val loadState  = DataListenerContainer<MusicLoadState>()

    private val page = 1
    private val size = 30
    fun getMusic() {
        loadState.value = MusicLoadState.LOADING
        //从model层获取音乐列表
        musicModel.loadMusicByPage(page,size,object :MusicModel.OnMusicLoadResult{
            override fun onSuccess(result: List<Music>) {
                musicList.value = result
                loadState.value =  if (result.isEmpty()){
                    MusicLoadState.EMPTY
                }else{
                    MusicLoadState.SUCCESS
                }
            }

            override fun onError(msg: String, code: Int) {
                loadState.value = MusicLoadState.ERROR
                println("error...$msg,$code")
            }

        })
    }

    override fun onCreate() {
        //监听GPS信号变化等等
        println("开始监听GPS")
        //开始监听网络变化
    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {
        //停止监听GPS
        println("停止监听GPS")
        //停止监听网络变化
    }

    override fun onDestroy() {

    }
}