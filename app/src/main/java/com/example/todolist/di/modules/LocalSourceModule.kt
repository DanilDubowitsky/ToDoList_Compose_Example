package com.example.todolist.di.modules

import com.example.todolist.data.core.source.point.ITaskPointLocalSource
import com.example.todolist.data.core.source.point.TaskPointLocalSource
import com.example.todolist.data.core.source.task.DayTaskLocalSource
import com.example.todolist.data.core.source.task.IDayTaskLocalSource
import com.example.todolist.data.database.ApplicationDataBase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LocalSourceModule {

    @Provides
    @Reusable
    fun provideDayTaskLocalSource(
        dataBase: ApplicationDataBase
    ): IDayTaskLocalSource =
        DayTaskLocalSource(dataBase.dayTaskDao)

    @Provides
    @Reusable
    fun provideTaskPointLocalSource(
        dataBase: ApplicationDataBase
    ): ITaskPointLocalSource = TaskPointLocalSource(dataBase.taskPointDao)
}
