package com.kj.memory_helper

import android.app.Application
import com.kj.memory_helper.monitor.*

/**
 * 对外暴漏的接口
 */
class Helper private constructor() {
    companion object {
        @Volatile
        private var instance: Helper? = null

        fun getInstance(): Helper {
            return instance ?: synchronized(Helper::class.java) {
                instance ?: Helper().also { instance = it }
            }
        }
    }

    private val monitors = mutableListOf<Monitor>()

    fun addMonitor(monitor: Monitor): Helper {
        monitors.add(monitor)
        return this
    }

    var application: Application? = null

    fun init(context: Application) {
        this.application = context
        NotifyManager.initNotificationManager(context)
        monitors.forEach {
            it.init(context)
        }
    }
}