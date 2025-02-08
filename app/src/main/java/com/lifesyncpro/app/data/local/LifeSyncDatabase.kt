package com.lifesyncpro.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lifesyncpro.app.data.local.converter.DateConverter
import com.lifesyncpro.app.data.local.converter.ListConverter
import com.lifesyncpro.app.data.local.dao.UserDao
import com.lifesyncpro.app.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, ListConverter::class)
abstract class LifeSyncDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "lifesync_db"
    }
} 