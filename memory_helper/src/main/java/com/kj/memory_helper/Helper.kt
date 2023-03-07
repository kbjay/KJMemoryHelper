package com.kj.memory_helper

import android.app.Application
import com.kj.memory_helper.monitor.*

/**
 * 对外暴漏的接口
 */
class Helper {
    companion object {
        var context: Application? = null
        fun init(ctx: Application) {
            // 反射私有api（sp）
//            Reflection.unseal(ctx)

            NotifyManager.initNotificationManager(ctx)

            context = ctx
            val imageMonitor = ImageMonitor()
            imageMonitor.init(ctx)
//
            val viewBgMonitor = ViewBgMonitor()
            viewBgMonitor.init(ctx)
////
            val spGetMonitor = SPGetMonitor()
            spGetMonitor.init(ctx)
//
            val spPutMonitor = SPPutMonitor()
            spPutMonitor.init(ctx)
//
            val rvMonitor = RVMonitor()
            rvMonitor.init(ctx)

        }
    }
}