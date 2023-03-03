package com.kj.memory_helper.db

import android.app.Application
import android.content.Context
import androidx.room.Room

class DbProvider private constructor(context: Context) {

    val db = Room.databaseBuilder(
        context,
        HelperDatabase::class.java, "database-name"
    ).allowMainThreadQueries().build()

    companion object {
        @Volatile
        private var instance: DbProvider? = null
        fun getInstance(context: Application) =
            instance ?: synchronized(this) {
                instance ?: DbProvider(context).also {
                    instance = it
                }
            }
    }
}