package com.example.todolist.di.modules

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.database.ApplicationDataBase
import com.example.todolist.data.database.ApplicationDataBase.Companion.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ): ApplicationDataBase {
        return Room.databaseBuilder(
            context,
            ApplicationDataBase::class.java, DATA_BASE_NAME
        ).build()
    }
}