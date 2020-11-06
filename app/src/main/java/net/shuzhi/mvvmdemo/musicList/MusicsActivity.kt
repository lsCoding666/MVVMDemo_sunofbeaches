package net.shuzhi.mvvmdemo.musicList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_musics.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.base.BaseActivity

/**
 * @author 梁爽
 * @create 2020/11/4 23:19
 */
class MusicsActivity : BaseActivity() {

    private val musicPresenter by lazy { MusicPresenter(this) }

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

        musicPresenter.liveMusicList.observe(
            this,
            Observer {
                println("live data里的音乐列表数据更新了${it?.size}条数据")
                musicCountText?.text = "更新了${it?.size}条数据"
            }
        )

        musicPresenter.musicList.addListener(this) {
            println(Thread.currentThread().name)
            //数据变化
            println("加载状态 ---> ${it?.size}")
            musicCountText?.text = "加载到了 ---> ${it?.size} 条数据"
        }
        musicPresenter.loadState.addListener(this) {
            println("加载状态 ---> $it")
        }
    }

    private fun initViewListeners() {
        getMusicListBtn.setOnClickListener {
            musicPresenter.getMusic()
        }
    }
}