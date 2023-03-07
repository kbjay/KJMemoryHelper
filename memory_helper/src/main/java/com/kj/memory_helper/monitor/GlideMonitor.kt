package com.kj.memory_helper.monitor

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.cache.MemoryCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.kj.memory_helper.PhoneLevel
import com.kj.memory_helper.db.WarningMsg
import java.lang.StringBuilder

/**
 * 低端机,bitmapPool 0.5;memory 1
 */
class GlideMonitor(val level: PhoneLevel = PhoneLevel.High) : SampleMonitor() {
    override fun init(context: Context) {
        val bitmapPool = Glide.get(context).bitmapPool
        val bitmapSize = bitmapPool.maxSize.toInt()

        val field = Glide::class.java.getDeclaredField("memoryCache")
        field.isAccessible = true
        val memoryCache = field.get(Glide.get(context)) as MemoryCache
        val memorySize = memoryCache.maxSize.toInt()

        Glide.get(context).clearMemory()
        val calculator = MemorySizeCalculator.Builder(context)
            .setBitmapPoolScreens(0.5f)
            .setMemoryCacheScreens(1f)
            .build()

        if (level == PhoneLevel.Low) {
            val sb = StringBuilder()

            if (memorySize > calculator.memoryCacheSize) {
                sb.append("MemoryCache大小不合格（低端机建议1f）")
            }
            if (bitmapSize > calculator.bitmapPoolSize) {
                sb.append("BitMapPool大小不合格（低端机建议0.5f）")
            }

            if (sb.toString() != "") {
                warning(
                    WarningMsg(
                        "GlideMonitor",
                        sb.toString(),
                        "@GlideModule  AppGlideModule"
                    )
                )
            }
        }
    }
}