package net.shuzhi.mvvmdemo.taobao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

/**
 * @author 梁爽
 * @create 2020/11/6 19:11
 */
class OnSellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel= ViewModelProvider(this).get(OnSellViewModel::class.java)
        viewModel.loadContent()
    }
}