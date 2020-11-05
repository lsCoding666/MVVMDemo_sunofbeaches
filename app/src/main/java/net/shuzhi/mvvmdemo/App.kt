package net.shuzhi.mvvmdemo

import android.app.Application
import android.os.Handler

/**
 * @author 梁爽
 * @create 2020/11/5 10:42
 */
class App : Application() {

    companion object{
        val handler = Handler()
    }

    override fun onCreate() {
        super.onCreate()

    }
}