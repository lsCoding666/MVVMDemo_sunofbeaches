package net.shuzhi.mvvmdemo.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_flow_player.*
import net.shuzhi.mvvmdemo.R

/**
 * @author 梁爽
 * @create 2020/11/3 20:05
 */
class FlowPlayerControllerActivity : AppCompatActivity(), IPlayerCallback {
    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player)
        initListeners()
    }

    private fun initListeners() {
        playOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onTitleChange(title: String) {
        TODO("Not yet implemented")
    }

    override fun onProgressChange(current: Int) {
    }

    override fun onPlaying() {
        playOrPauseBtn.text = "暂停"
    }

    override fun onPlayerPause() {
        playOrPauseBtn.text = "播放"
    }

    override fun onCoverChange(cover: String) {
        TODO("Not yet implemented")
    }
}