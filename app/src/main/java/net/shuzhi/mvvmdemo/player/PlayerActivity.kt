package net.shuzhi.mvvmdemo.player

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_player.*
import net.shuzhi.mvvmdemo.R

/**
 * @author 梁爽
 * @create 2020/10/31 16:59
 */
class PlayerActivity : AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy { PlayerPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerPresenter.registerCallback(this)
        initListeners()
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

    override fun onTitleChange(title: String) {
        songTitle.text = title
    }

    override fun onProgressChange(current: Int) {
        TODO("Not yet implemented")
    }

    override fun onPlaying() {
        playerOrPauseBtn.text = "暂停"
    }

    override fun onPlayerPause() {
        playerOrPauseBtn.text = "播放"
    }

    override fun onCoverChange(cover: String) {
        println("封面更新$cover")
    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }
}