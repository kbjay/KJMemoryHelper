package com.kj.memory_helper.monitor

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.kj.memory_helper.db.WarningMsg
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook

class ImageMonitor : SampleMonitor() {
    override fun init(context: Context) {
        DexposedBridge.findAndHookMethod(
            ImageView::class.java,
            "updateDrawable",
            Drawable::class.java,
            ImageHook(this, context)
        )
    }

    internal class ImageHook(var monitor: Monitor, var context: Context) :
        XC_MethodHook() {
        @Throws(Throwable::class)
        override fun afterHookedMethod(param: MethodHookParam) {
            super.afterHookedMethod(param)
            try {
                val throwable = Throwable()
                val arg = param.args[0] as Drawable
                val bitmap = (arg as BitmapDrawable).bitmap
                val view = param.thisObject as ImageView
                var name = "未命名"
                if (view.id != -1)
                    name = context.resources.getResourceEntryName(view.id)
                view.post {
                    if (bitmap.width > view.width && bitmap.height > view.height) {
                        monitor.warning(
                            WarningMsg(
                                ImageMonitor::class.simpleName ?: "ImageMonitor",
                                "图片不合格!  name:${name} bitmap:${bitmap.width} ${bitmap.height} view:${view.width} ${view.height}",
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