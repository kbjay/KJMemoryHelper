package com.kj.memory_helper.monitor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.kj.memory_helper.PhoneLevel
import com.kj.memory_helper.db.WarningMsg
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook

/**
 * 低端机：
 * 尺寸：2/3
 * 格式：565
 *
 * 高中端：
 * 尺寸：1
 */
class ImageMonitor(val level: PhoneLevel = PhoneLevel.High) : SampleMonitor() {
    override fun init(context: Context) {
        DexposedBridge.findAndHookMethod(
            ImageView::class.java,
            "updateDrawable",
            Drawable::class.java,
            ImageHook(this, context, level)
        )
    }

    internal class ImageHook(var monitor: Monitor, var context: Context, val level: PhoneLevel) :
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
                    when (level) {
                        PhoneLevel.High, PhoneLevel.Middle -> {
                            if (bitmap.width > view.measuredWidth && bitmap.height > view.measuredHeight) {
                                monitor.warning(
                                    WarningMsg(
                                        "ImageMonitor_size",
                                        "图片尺寸不合格!  name:${name} id:${view.id}",
                                        "bitmap:${bitmap.width} ${bitmap.height} view:${view.measuredWidth} ${view.measuredHeight} \r\n ${throwable.stackTraceToString()}"
                                    )
                                )
                            }
                        }
                        PhoneLevel.Low -> {
                            if (bitmap.width > view.measuredWidth * 0.67 && bitmap.height > view.measuredHeight * 0.67) {
                                monitor.warning(
                                    WarningMsg(
                                        "ImageMonitor_size",
                                        "图片尺寸不合格!(低端机建议2/3)  name:${name} id:${view.id}",
                                        "bitmap:${bitmap.width} ${bitmap.height} view:${view.measuredWidth} ${view.measuredHeight} \r\n ${throwable.stackTraceToString()}"
                                    )
                                )
                            }
                            if (bitmap.config != Bitmap.Config.RGB_565){
                                monitor.warning(
                                    WarningMsg(
                                        "ImageMonitor_rgb",
                                        "图片不合格!(低端机建议565)  name:${name} id:${view.id}",
                                        throwable.stackTraceToString()
                                    )
                                )
                            }
                        }
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