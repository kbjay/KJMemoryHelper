package com.kj.memoryhelper

import android.app.Application
import com.kj.memory_helper.Helper

class MHAPP : Application() {
    override fun onCreate() {
        super.onCreate()
        Helper.init(this)
    }
}