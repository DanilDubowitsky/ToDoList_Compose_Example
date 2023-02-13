package com.example.todolist.data.core.source.point

import com.example.todolist.data.converters.toModels
import com.example.todolist.data.dao.TaskPointDao
import com.example.todolist.data.entity.point.TaskPointEntity
import com.example.todolist.domain.model.TaskPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskPointLocalSource(
    private val taskPointDao: TaskPointDao
) : ITaskPointLocalSource {

    override suspend fun updateCompleteState(isCompleted: Boolean, id: Long) =
        taskPointDao.updateCompleteState(isCompleted, id)

    override suspend fun getTaskPoints(taskId: Long): Flow<List<TaskPoint>> =
        taskPointDao.getPoints(taskId).map(List<TaskPointEntity>::toModels)
}
