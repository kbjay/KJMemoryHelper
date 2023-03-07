package com.kj.memoryhelper

import android.app.Application
import com.kj.memory_helper.Helper
import com.kj.memory_helper.PhoneLevel
import com.kj.memory_helper.monitor.*

class MHAPP : Application() {

    override fun onCreate() {
        super.onCreate()
        Helper.getInstance()
            .addMonitor(SPPutMonitor())
            .addMonitor(SPGetMonitor())
            .addMonitor(RVMonitor())
            .addMonitor(ImageMonitor(PhoneLevel.Low))
            .addMonitor(GlideMonitor(PhoneLevel.Low))
            .addMonitor(ViewBgMonitor())
            .init(this)
    }
}