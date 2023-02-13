package com.example.todolist.di.modules

import com.example.todolist.data.core.repository.DayTaskRepository
import com.example.todolist.data.core.repository.TaskPointRepository
import com.example.todolist.data.core.source.point.ITaskPointLocalSource
import com.example.todolist.data.core.source.task.IDayTaskLocalSource
import com.example.todolist.domain.repository.IDayTaskRepository
import com.example.todolist.domain.repository.ITaskPointRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @Reusable
    fun provideDayTaskRepository(
        localSource: IDayTaskLocalSource
    ): IDayTaskRepository =
        DayTaskRepository(localSource)

    @Provides
    @Reusable
    fun provideTaskPointRepository(
        localSource: ITaskPointLocalSource
    ): ITaskPointRepository = TaskPointRepository(localSource)
}
