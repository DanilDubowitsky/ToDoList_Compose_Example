package com.example.todolist.di.modules

import com.example.todolist.domain.cases.GetTaskPointsReactive
import com.example.todolist.domain.cases.UpdateTaskPointCompleteState
import com.example.todolist.domain.repository.ITaskPointRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object TaskPointCasesModule {

    @Provides
    @Reusable
    fun provideUpdateTaskPointCompleteState(
        repository: ITaskPointRepository
    ): UpdateTaskPointCompleteState = UpdateTaskPointCompleteState(repository)

    @Provides
    @Reusable
    fun provideGetTaskPointsReactive(
        repository: ITaskPointRepository
    ): GetTaskPointsReactive = GetTaskPointsReactive(repository)
}
