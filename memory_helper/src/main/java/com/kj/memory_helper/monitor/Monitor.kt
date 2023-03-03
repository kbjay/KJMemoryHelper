package com.kj.memory_helper.monitor

import com.kj.memory_helper.db.WarningMsg

/**
 * 发出提示
 */
interface Monitor {
    fun init()
    fun warning(msg: WarningMsg)
}