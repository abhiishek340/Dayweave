package com.lifesyncpro.app.di

import android.content.Context
import androidx.room.Room
import com.lifesyncpro.app.data.local.LifeSyncDatabase
import com.lifesyncpro.app.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): LifeSyncDatabase {
        return Room.databaseBuilder(
            context,
            LifeSyncDatabase::class.java,
            LifeSyncDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: LifeSyncDatabase): UserDao {
        return database.userDao()
    }
} 