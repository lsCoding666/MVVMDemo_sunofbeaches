package net.shuzhi.mvvmdemo.taobao

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlinx.android.synthetic.main.activity_on_error.*
import kotlinx.android.synthetic.main.activity_on_sell.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.adapter.OnSellListAdapter
import net.shuzhi.mvvmdemo.base.LoadState
import net.shuzhi.mvvmdemo.utils.SizeUtils

/**
 * @author 梁爽
 * @create 2020/11/6 19:11
 */
class OnSellActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        OnSellListAdapter()
    }

    private val mViewModel by lazy {
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
                mAdapter.setData(it)
            })
            loadState.observe(this@OnSellActivity, Observer {
                if (it != LoadState.LOADER_MORE_LOADING) {
                    hideAll()
                }
                //根据加载的状态来更新ui的显示
                when (it) {
                    LoadState.LOADING -> {
                        loadingView.visibility = View.VISIBLE
                    }
                    LoadState.EMPTY -> {
                        emptyView.visibility = View.VISIBLE
                    }
                    LoadState.ERROR -> {
                        errorView.visibility = View.VISIBLE
                    }
                    LoadState.SUCCESS -> {
                        contentRefreshView.visibility = View.VISIBLE
                        contentRefreshView.finishLoadmore()
                    }
                    LoadState.LOADER_MORE_ERROR -> {
                        contentRefreshView.visibility = View.VISIBLE
                        Toast.makeText(this@OnSellActivity, "网络不佳 请稍后重试", Toast.LENGTH_SHORT).show()
                        contentRefreshView.finishLoadmore()
                    }
                    LoadState.LOADER_MORE_EMPTY -> {
                        Toast.makeText(this@OnSellActivity, "没有更多内容了", Toast.LENGTH_SHORT).show()
                        contentRefreshView.visibility = View.VISIBLE
                        contentRefreshView.finishLoadmore()
                    }
                    else -> {

                    }
                }
            })
        }.loadContent()
    }

    /**
     * 初始化View
     */
    private fun initView() {
        contentRefreshView.run {
            setEnableLoadmore(true)
            setEnableRefresh(true)
            setEnableOverScroll(true)
            setOnRefreshListener(object : RefreshListenerAdapter() {
                override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                    //去执行加载更多
                    mViewModel.loaderMode()
                }
            })
        }
        reloadLl.setOnClickListener {
            //重新加载数据
            mViewModel.loadContent()
        }
        contentListRv.run {
            layoutManager = LinearLayoutManager(this@OnSellActivity)
            adapter = mAdapter
            addItemDecoration(
                object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        outRect.apply {
                            val padding = SizeUtils.dip2px(this@OnSellActivity, 4.0f)
                            top = padding
                            left = padding
                            bottom = padding
                            right = padding
                        }
                    }
                }
            )
        }
    }

    private fun hideAll() {
        contentRefreshView.visibility = View.GONE
        errorView.visibility = View.GONE
        loadingView.visibility = View.GONE
        emptyView.visibility = View.GONE
    }
}