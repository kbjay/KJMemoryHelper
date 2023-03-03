package com.kj.memory_helper.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WarningMsg::class], version = 1, exportSchema = false)
abstract class HelperDatabase : RoomDatabase() {
    abstract fun warningMsgDao(): WarningMsgDao
}