package net.shuzhi.mvvmdemo.musicList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_musics.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.base.BaseActivity

/**
 * @author 梁爽
 * @create 2020/11/4 23:19
 */
class MusicsActivity : BaseActivity() {

    private val musicPresenter by lazy { MusicPresenter() }

    init{
        addLifeListener(musicPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musics)
        initDataListener()
        initViewListeners()
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