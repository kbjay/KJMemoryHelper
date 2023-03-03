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
            NotifyManager.initNotificationManager(ctx)

            context = ctx
            val imageMonitor = ImageMonitor()
            imageMonitor.init()

            val viewBgMonitor = ViewBgMonitor()
            viewBgMonitor.init()
////
//            val spGetMonitor = SPGetMonitor()
//            spGetMonitor.init()
//
//            val spPutMonitor = SPPutMonitor()
//            spPutMonitor.init()

        }
    }
}