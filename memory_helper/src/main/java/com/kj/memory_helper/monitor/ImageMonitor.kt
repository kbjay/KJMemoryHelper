package com.kj.memory_helper.monitor

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.kj.memory_helper.db.WarningMsg
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook

class ImageMonitor : SampleMonitor() {
    override fun init() {
        DexposedBridge.findAndHookMethod(
            ImageView::class.java,
            "updateDrawable",
            Drawable::class.java,
            ImageHook(this)
        )
    }

    internal class ImageHook(var monitor: Monitor) :
        XC_MethodHook() {
        @Throws(Throwable::class)
        override fun afterHookedMethod(param: MethodHookParam) {
            super.afterHookedMethod(param)
            try {
                val throwable = Throwable()
                val arg = param.args[0] as Drawable
                val bitmap = (arg as BitmapDrawable).bitmap
                val view = param.thisObject as ImageView
                view.post {
                    if (bitmap.width > view.width && bitmap.height > view.height) {
                        monitor.warning(
                            WarningMsg(
                                ImageMonitor::class.simpleName ?: "ImageMonitor",
                                "${view.id} 图片不合格! ${bitmap.width} ${bitmap.height} ${view.width} ${view.height}",
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