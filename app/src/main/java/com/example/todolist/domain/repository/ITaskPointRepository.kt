package com.example.todolist.domain.repository

import com.example.todolist.domain.model.TaskPoint
import kotlinx.coroutines.flow.Flow

interface ITaskPointRepository {
    suspend fun updateCompleteState(isCompleted: Boolean, id: Long)
    suspend fun getTaskPoints(taskId: Long): Flow<List<TaskPoint>>
}