package net.shuzhi.mvvmdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.shuzhi.mvvmdemo.lifecycle.ILifeCircle
import net.shuzhi.mvvmdemo.musicList.MusicPresenter

/**
 * @author 梁爽
 * @create 2020/11/5 12:14
 */
open class BaseActivity : AppCompatActivity() {

    private val lifeCircleListener = arrayListOf<ILifeCircle>()

    fun addLifeListener(listener:ILifeCircle){
        if (!lifeCircleListener.contains(listener)){
            lifeCircleListener.add(listener)
        }
    }

    fun removeLifeCircleListener(listener: ILifeCircle){
        if (lifeCircleListener.contains(listener)){
            lifeCircleListener.remove(listener)
        }
    }

    private val musicPresenter by lazy { MusicPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeCircleListener.forEach{
            it.onCreate()
        }
    }

    override fun onStart() {
        super.onStart()
        lifeCircleListener.forEach{
            it.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        lifeCircleListener.forEach{
            it.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        lifeCircleListener.forEach{
            it.onPause()
        }
    }

    override fun onStop() {
        super.onStop()
        lifeCircleListener.forEach{
            it.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeCircleListener.forEach{
            it.onDestroy()
        }
    }
}