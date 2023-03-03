package com.kj.memory_helper.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warning_msg")
data class WarningMsg(
    val type: String,
    val title: String,
    val content: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    ) {
    override fun equals(other: Any?): Boolean {
        return other is WarningMsg
                && other.type == type
                && other.title == title
    }
}
