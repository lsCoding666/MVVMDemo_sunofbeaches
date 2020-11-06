package net.shuzhi.mvvmdemo.api

import java.lang.RuntimeException

/**
 * @author 梁爽
 * @create 2020/11/6 20:03
 */
data class ApiException(val code:Int,override val message:String?):RuntimeException() {
}