package com.example.todolist.core

import com.example.todolist.domain.model.TaskPoint
import com.example.todolist.domain.repository.ITaskPointRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MockTaskPointsRepository : ITaskPointRepository {
    private val mockTaskPoints = listOf(
        TaskPoint(5, 1, "Test Body", true),
        TaskPoint(4, 1, "Test Body1", true),
    )
    override suspend fun updateCompleteState(isCompleted: Boolean, id: Long) {}

    override suspend fun getTaskPoints(taskId: Long): Flow<List<TaskPoint>> =
        flowOf(mockTaskPoints)
}
