package com.example.todolist.data.core.repository

import com.example.todolist.data.core.source.point.ITaskPointLocalSource
import com.example.todolist.domain.model.TaskPoint
import com.example.todolist.domain.repository.ITaskPointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class TaskPointRepository(
    private val taskPointLocalSource: ITaskPointLocalSource
) : ITaskPointRepository {

    override suspend fun updateCompleteState(isCompleted: Boolean, id: Long) =
        taskPointLocalSource.updateCompleteState(isCompleted, id)

    override suspend fun getTaskPoints(taskId: Long): Flow<List<TaskPoint>> =
        taskPointLocalSource.getTaskPoints(taskId).distinctUntilChanged()
}
