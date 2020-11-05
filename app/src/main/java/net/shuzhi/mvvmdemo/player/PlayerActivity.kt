package net.shuzhi.mvvmdemo.player

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_player.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.base.BaseActivity
import net.shuzhi.mvvmdemo.musicList.MusicPresenter
import net.shuzhi.mvvmdemo.player.domain.Music

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

    /**
     * 对数据进行监听
     */
    private fun initDataListeners() {
        playerPresenter.currentMusic.addListener {
            //更新ui
            songTitle.text = it?.name
            println("封面改变了...${it?.cover}")
        }
        playerPresenter.currentPlayState.addListener {
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
    }
}