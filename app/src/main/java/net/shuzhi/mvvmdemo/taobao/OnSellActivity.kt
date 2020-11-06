package net.shuzhi.mvvmdemo.taobao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_on_sell.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.adapter.OnSellListAdapter

/**
 * @author 梁爽
 * @create 2020/11/6 19:11
 */
class OnSellActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        OnSellListAdapter()
    }

    private val mViewModel by lazy{
        ViewModelProvider(this).get(OnSellViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_sell)
        initView()
        initObserver()
    }

    /**
     * 观察数据变化
     */
    private fun initObserver() {
        mViewModel.apply {
            contentList.observe(this@OnSellActivity, Observer {
                //内容列表更新
                //TODO:更新适配器
                println("数据变化了")
                mAdapter.setData(it)
            })
        }.loadContent()
    }

    /**
     * 初始化View
     */
    private fun initView() {
        contentListRv.run {
            layoutManager = LinearLayoutManager(this@OnSellActivity)
            adapter = mAdapter
        }
    }
}