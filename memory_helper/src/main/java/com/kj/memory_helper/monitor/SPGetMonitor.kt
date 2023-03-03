package com.kj.memory_helper.monitor

import android.content.SharedPreferences
import com.kj.memory_helper.db.WarningMsg
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook

/**
 * android hide api 反射限制！！
 */
class SPGetMonitor : SampleMonitor() {
    override fun init() {
        DexposedBridge.findAndHookMethod(
            SharedPreferences::class.java,
            "getString",
            String::class.java,
            String::class.java,
            SPGetMethodHook(this)
        )
    }

    internal class SPGetMethodHook(private val monitor: Monitor) : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam?) {
            super.afterHookedMethod(param)
            try {
                val throwable = Throwable()
                val value = param?.result as String
                val key = param.args?.get(0) as String
                if (value.length > 100) {
                    monitor.warning(
                        WarningMsg(
                            SPGetMonitor::class.simpleName ?: "SPGetMonitor",
                            "SharedPreferences使用不合格：数据量过大。$key - ${value.length}",
                            throwable.stackTraceToString()
                        )
                    )
                }
            } catch (e: Exception) {
            }
        }
    }
}