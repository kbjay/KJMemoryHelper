package com.kj.memoryhelper

import android.app.Application
import android.content.Context
import com.kj.memory_helper.Helper

class MHAPP : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Helper.init(this)

    }
}