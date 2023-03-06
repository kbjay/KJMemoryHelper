package com.kj.memory_helper.monitor

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kj.memory_helper.db.WarningMsg
import de.robv.android.xposed.DexposedBridge
import de.robv.android.xposed.XC_MethodHook
import java.lang.Exception
import java.lang.StringBuilder

/**
 * 思路：参考leakcanary 结合epic，hook onviewRecycled方法，获取参数viewholder，获取itemview，如果
 */
class RVMonitor : SampleMonitor() {
    override fun init(context: Context) {
        DexposedBridge.findAndHookMethod(
            RecyclerView.Adapter::class.java,
            "onViewRecycled",
            RecyclerView.ViewHolder::class.java,
            RvMethodHook(this, context)
        )
    }

    class RvMethodHook(private val monitor: Monitor, private val context: Context) :
        XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam?) {
            super.afterHookedMethod(param)
            // 拿到vh
            // 反射拿到所有的属性，如果是imageview，那么需要是null！！否则warning
            try {
                val throwable = Throwable()
                val vh = param?.args?.get(0)
                val clazz = vh?.javaClass
                val fields = clazz?.declaredFields
                val warning = StringBuilder()
                fields?.forEach {
                    if (it.type.isAssignableFrom(ImageView::class.java)) {
                        it.isAccessible = true
                        val iv = it.get(vh) as ImageView
                        if (iv.drawable != null)
                            warning.append(context.resources.getResourceEntryName(iv.id))
                                .append("：未释放")
                    }
                }
                if (warning.toString() != "") {
                    monitor.warning(
                        WarningMsg(
                            "RVMonitor",
                            clazz?.name + " " + warning.toString(),
                            throwable.stackTraceToString()
                        )
                    )
                }
            } catch (e: Exception) {

            }


        }
    }
}