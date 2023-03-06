package com.kj.memory_helper.monitor

import android.content.Context
import com.kj.memory_helper.db.WarningMsg

/**
 * 发出提示
 */
interface Monitor {
    fun init(context: Context)
    fun warning(msg: WarningMsg)
}