package net.shuzhi.mvvmdemo.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_flow_player.*
import kotlinx.android.synthetic.main.activity_player.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.base.BaseActivity

/**
 * @author 梁爽
 * @create 2020/11/3 20:05
 */
class FlowPlayerControllerActivity : BaseActivity(){
    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player)
        initListeners()
        initDataListener()
    }

    private fun initDataListener() {
        playerPresenter.currentPlayState.addListener(this){
            playerOrPauseBtn.text = if (it === PlayerPresenter.PlayState.PLAYING){
                "暂停"
            }else{
                "播放"
            }
        }
    }

    private fun initListeners() {
        playOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }
}