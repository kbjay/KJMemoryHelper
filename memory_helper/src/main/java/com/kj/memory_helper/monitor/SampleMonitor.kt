package com.kj.memory_helper.monitor

import com.kj.memory_helper.Helper
import com.kj.memory_helper.NotifyManager
import com.kj.memory_helper.WarningRepository
import com.kj.memory_helper.db.WarningMsg

abstract class SampleMonitor : Monitor {

    override fun warning(msg: WarningMsg) {
        WarningRepository.data.forEach {
            if (it == msg)
                return
        }
        NotifyManager.showNotification(Helper.getInstance().application!!, msg)
        WarningRepository.insert(msg)
    }
}