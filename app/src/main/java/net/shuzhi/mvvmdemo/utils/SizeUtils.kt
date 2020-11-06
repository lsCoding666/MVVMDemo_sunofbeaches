package net.shuzhi.mvvmdemo.utils

import android.content.Context

/**
 * @author 梁爽
 * @create 2020/11/6 21:27
 */
object SizeUtils {
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}