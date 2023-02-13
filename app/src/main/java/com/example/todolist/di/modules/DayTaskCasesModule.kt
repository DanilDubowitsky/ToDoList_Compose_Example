package com.example.todolist.di.modules

import com.example.todolist.domain.cases.CreateTask
import com.example.todolist.domain.cases.GetDayTask
import com.example.todolist.domain.cases.GetTasksReactive
import com.example.todolist.domain.repository.IDayTaskRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DayTaskCasesModule {

    @Provides
    @Reusable
    fun provideCreateTask(
        repository: IDayTaskRepository
    ): CreateTask = CreateTask(repository)

    @Provides
    @Reusable
    fun provideGetDayTasksReactive(
        repository: IDayTaskRepository
    ): GetTasksReactive =
        GetTasksReactive(repository)

    @Provides
    @Reusable
    fun provideGetTask(
        repository: IDayTaskRepository
    ): GetDayTask = GetDayTask(repository)
}
