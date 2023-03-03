package com.kj.memory_helper

import androidx.lifecycle.LiveData
import com.kj.memory_helper.db.DbProvider
import com.kj.memory_helper.db.WarningMsg
import java.lang.RuntimeException

class WarningRepository {
    companion object {
        fun insert(warningMsg: WarningMsg) {
            if (Helper.context != null) {
                DbProvider.getInstance(Helper.context!!).db.warningMsgDao().insert(warningMsg)
            } else {
                throw RuntimeException("先init")
            }
        }

        fun getAllAsync(): LiveData<List<WarningMsg>> {
            if (Helper.context != null) {
                return DbProvider.getInstance(Helper.context!!).db.warningMsgDao().getAllAsync()
            } else {
                throw RuntimeException("先init")
            }
        }

        fun getAllSync(): List<WarningMsg> {
            if (Helper.context != null) {
                return DbProvider.getInstance(Helper.context!!).db.warningMsgDao().getAllSync()
            } else {
                throw RuntimeException("先init")
            }
        }
    }
}