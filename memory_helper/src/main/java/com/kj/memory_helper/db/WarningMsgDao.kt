package com.kj.memory_helper.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WarningMsgDao {
    @Query("select * from warning_msg")
    fun getAllAsync(): LiveData<List<WarningMsg>>

    @Query("select * from warning_msg")
    fun getAllSync(): List<WarningMsg>

    @Insert
    fun insert(msg: WarningMsg)
}