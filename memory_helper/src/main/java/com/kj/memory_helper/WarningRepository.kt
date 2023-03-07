package com.kj.memory_helper

import androidx.lifecycle.LiveData
import com.kj.memory_helper.db.DbProvider
import com.kj.memory_helper.db.WarningMsg
import java.lang.RuntimeException

class WarningRepository {
    companion object {
        val data by lazy {
            getAllSync()
        }

        fun insert(warningMsg: WarningMsg) {
            if (Helper.getInstance().application != null) {
                DbProvider.getInstance(Helper.getInstance().application!!).db.warningMsgDao()
                    .insert(warningMsg)
                data.add(warningMsg)
            } else {
                throw RuntimeException("先init")
            }
        }

        fun getAllAsync(): LiveData<List<WarningMsg>> {
            if (Helper.getInstance().application != null) {
                return DbProvider.getInstance(Helper.getInstance().application!!).db.warningMsgDao()
                    .getAllAsync()
            } else {
                throw RuntimeException("先init")
            }
        }

        fun getAllSync(): MutableList<WarningMsg> {
            if (Helper.getInstance().application != null) {
                return DbProvider.getInstance(Helper.getInstance().application!!).db.warningMsgDao()
                    .getAllSync()
            } else {
                throw RuntimeException("先init")
            }
        }
    }
}