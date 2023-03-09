package com.kj.memory_helper.monitor

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.children
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
            RecyclerView.Recycler::class.java,
            "dispatchViewRecycled",
            RecyclerView.ViewHolder::class.java,
            RvMethodHook(this, context)
        )
    }

    class RvMethodHook(private val monitor: Monitor, private val context: Context) :
        XC_MethodHook() {
        fun getAllImage(v: View): ArrayList<ImageView> {
            val result = ArrayList<ImageView>()
            if (v is ImageView) {
                result.add(v)
                return result
            }
            if (v is ViewGroup) {
                v.children.forEach {
                    result.addAll(getAllImage(it))
                }
            }
            return result
        }


        override fun afterHookedMethod(param: MethodHookParam?) {
            super.afterHookedMethod(param)
            // 拿到vh
            // 反射拿到所有的属性，如果是imageview，那么需要是null！！否则warning
            try {
                val throwable = Throwable()
                val vh = param?.args?.get(0)
                val clazz = vh?.javaClass
                val declaredField = clazz?.getField("itemView")
                declaredField?.isAccessible=true
                val itemView = declaredField?.get(vh) as View
                val warning = StringBuilder()

                getAllImage(itemView).forEach{
                    if (it.drawable != null)
                        warning.append(context.resources.getResourceEntryName(it.id))
                            .append("：未释放")
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