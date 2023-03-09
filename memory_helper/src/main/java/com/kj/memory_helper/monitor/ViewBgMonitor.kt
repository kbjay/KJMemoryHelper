package com.kj.memory_helper.monitor

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import com.kj.memory_helper.db.WarningMsg
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook

class ViewBgMonitor : SampleMonitor() {
    override fun init(context: Context) {
        DexposedBridge.findAndHookMethod(
            View::class.java, "setBackground",
            Drawable::class.java, ViewBgHook(this, context)
        )
    }

    internal class ViewBgHook(val monitor: Monitor, val context: Context) :
        XC_MethodHook() {
        @Throws(Throwable::class)
        override fun afterHookedMethod(param: MethodHookParam) {
            super.afterHookedMethod(param)
            try {
                val throwable = Throwable()
                val arg = param.args[0] as Drawable
                val bitmap = (arg as BitmapDrawable).bitmap
                val view = param.thisObject as View
                var name = "未命名"
                if (view.id != -1)
                    name = context.resources.getResourceEntryName(view.id)
                view.post {
                    if (bitmap.width > view.measuredWidth && bitmap.height > view.measuredHeight) {
                        monitor.warning(
                            WarningMsg(
                                ViewBgMonitor::class.simpleName ?: "ViewBgMonitor",
                                "背景图尺寸不合格! name: $name  id：${view.id} bitmap:${bitmap.width} ${bitmap.height} view:${view.measuredWidth} ${view.measuredHeight}",
                                throwable.stackTraceToString()
                            )
                        )
                    }
                }
            } catch (e: Exception) {
            }
        }

        @Throws(Throwable::class)
        override fun beforeHookedMethod(param: MethodHookParam) {
            super.beforeHookedMethod(param)
        }
    }
}