package com.example.todolist.data.core.source.point

import com.example.todolist.domain.model.TaskPoint
import kotlinx.coroutines.flow.Flow

interface ITaskPointLocalSource {
    suspend fun updateCompleteState(isCompleted: Boolean, id: Long)
    suspend fun getTaskPoints(taskId: Long): Flow<List<TaskPoint>>
}