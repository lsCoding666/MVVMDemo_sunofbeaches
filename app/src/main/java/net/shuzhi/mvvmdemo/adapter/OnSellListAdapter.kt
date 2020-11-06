package net.shuzhi.mvvmdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_on_sell.view.*
import net.shuzhi.mvvmdemo.R
import net.shuzhi.mvvmdemo.domain.MapData

/**
 * @author 梁爽
 * @create 2020/11/6 20:46
 */
class OnSellListAdapter : RecyclerView.Adapter<OnSellListAdapter.InnerHolder>() {

    private val mContentList = arrayListOf<MapData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_on_sell, parent, false)
        return InnerHolder(itemView)
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.itemView.apply {
            with(mContentList[position]) {
                itemTitleTv.text = title
            }
        }
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    fun setData(it: List<MapData>) {
        mContentList.clear()
        mContentList.addAll(it)
        notifyDataSetChanged()
    }
}