package com.kj.memory_helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.view.LayoutInflater.from
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kj.memory_helper.db.WarningMsg

object NotifyManager {
    private var notificationManager: NotificationManager? = null
    private val CHANNEL_ID = "memory_helper"
    private var notify_id = 1

    /**
     * 初始化NotificationManager
     */
    fun initNotificationManager(context: Context) {
        notificationManager = notificationManager
            ?: context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        //判断是否为8.0以上：Build.VERSION_CODES.O为26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //聊天消息渠道
            //创建通知渠道ID
            val chatChannelId = CHANNEL_ID
            //创建通知渠道名称
            val chatChannelName = "内存建议"
            //创建通知渠道重要性
            val chatImportance = NotificationManager.IMPORTANCE_HIGH
            //创建渠道
            val chatChannel = NotificationChannel(chatChannelId, chatChannelName, chatImportance)
            notificationManager?.createNotificationChannel(chatChannel)
        }
    }

    /**
     * 通知栏显示
     */
    fun showNotification(context: Context, warningMsg: WarningMsg) {
        val chatNotification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle(warningMsg.type)
            .setContentText(warningMsg.title)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setShowWhen(true)
            .build()
        NotificationManagerCompat.from(context).notify(notify_id, chatNotification)
        notify_id++
    }
}