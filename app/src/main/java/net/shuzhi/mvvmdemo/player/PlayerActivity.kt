package net.shuzhi.mvvmdemo.player

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_player.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.base.BaseActivity
import net.shuzhi.mvvmdemo.musicList.MusicPresenter
import net.shuzhi.mvvmdemo.player.domain.Music
import java.util.*

/**
 * @author 梁爽
 * @create 2020/10/31 16:59
 */
class PlayerActivity : BaseActivity() {

    private val playerPresenter by lazy { PlayerPresenter.instance }

    private val musicPresenter by lazy { MusicPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initListeners()
        initDataListeners()
    }

    fun toFlowPage(view:View){
        startActivity(Intent(this,FlowPlayerControllerActivity::class.java))
    }

    private val livePlayerObserver by lazy { LivePlayerStateObserver() }

    class  LivePlayerStateObserver:Observer<PlayerPresenter.PlayState>{
        override fun onChanged(t: PlayerPresenter.PlayState?) {
            println("播放器界面...live data --->  当前的状态 ---> $t")
        }
    }

    /**
     * 对数据进行监听
     */
    private fun initDataListeners() {

        LivePlayerState.instance.observeForever(livePlayerObserver)

        playerPresenter.currentMusic.addListener(this) {
            //更新ui
            songTitle.text = it?.name
            println("封面改变了...${it?.cover}")
        }
        playerPresenter.currentPlayState.addListener(this) {
            when (it) {
                PlayerPresenter.PlayState.PAUSE -> {
                    playerOrPauseBtn.text = "播放"
                }
                PlayerPresenter.PlayState.PLAYING -> {
                    playerOrPauseBtn.text = "暂停"
                }
            }
        }
    }

    private fun initListeners() {
        playerOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
        playPreBtn.setOnClickListener {
            playerPresenter.playPre()
        }
        playNextBtn.setOnClickListener {
            playerPresenter.playNext()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LivePlayerState.instance.removeObserver(livePlayerObserver)
    }
}