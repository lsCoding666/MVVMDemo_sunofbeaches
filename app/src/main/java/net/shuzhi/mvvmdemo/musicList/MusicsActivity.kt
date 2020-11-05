package net.shuzhi.mvvmdemo.musicList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_musics.*
import net.shuzhi.mvvmdemo.R

/**
 * @author 梁爽
 * @create 2020/11/4 23:19
 */
class MusicsActivity : AppCompatActivity() {

    private val musicPresenter by lazy { MusicPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musics)
        initDataListener()
        initViewListeners()
        musicPresenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        musicPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        musicPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        musicPresenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        musicPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPresenter.onDestroy()
    }

    /**
     * 监听数据变化
     */
    private fun initDataListener() {
        musicPresenter.musicList.addListener {
            println(Thread.currentThread().name)
            //数据变化
            println("加载状态 ---> ${it?.size}")
            musicCountText?.text="加载到了 ---> ${it?.size} 条数据"
        }
        musicPresenter.loadState.addListener {
            println("加载状态 ---> $it")
        }
    }

    private fun initViewListeners() {
        getMusicListBtn.setOnClickListener {
            musicPresenter.getMusic()
        }
    }
}